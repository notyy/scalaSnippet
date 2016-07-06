package reactiveComponent

import reactiveComponent.framework._

import scala.collection.concurrent.TrieMap
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object Platform {

  val cache: TrieMap[String, TrieMap[String, String]] = new TrieMap[String, TrieMap[String, String]]

  def run[A, B](simpleTransformation: SimpleTransformation[A, B])(input: A): Future[B] = {
    simpleTransformation.update(input)
  }

  def run[A, B](flow: Flow[A, B], input: A): Future[B] = {
    flow.runThrough(input)
  }

  def run[A <: KeyedInput, B, M](statefulComponent: => StatefulComponent[A, B, M])(input: A, model: Option[M]): Future[B] = {
    val optModel: Option[M] = for {
      processModelMap <- cache.get(statefulComponent.processName)
      model <- processModelMap.get(input.key)
    } yield statefulComponent.extract(model)

    //TODO ugly
    statefulComponent.update(optModel, input).map {
      case (Some(m), output) => {
        if (cache.contains(statefulComponent.processName)) {
          cache(statefulComponent.processName).update(input.key, statefulComponent.modelSerialize(m))
        } else {
          cache.update(statefulComponent.processName, TrieMap((input.key, statefulComponent.modelSerialize(m))))
        }
        output
      }
      case (None, output) => {
        if (cache.contains(statefulComponent.processName)) {
          cache(statefulComponent.processName) -= input.key
        }
        output
      }
    }
  }
}
