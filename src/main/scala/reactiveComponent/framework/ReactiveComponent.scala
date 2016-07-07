package reactiveComponent.framework

import com.typesafe.scalalogging.slf4j.StrictLogging
import reactiveComponent.framework.ComponentWithSideEffect.Result

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.language.postfixOps

trait ReactiveComponent[A, B] {
  def processName: String
}

trait SimpleTransformation[A, B] extends ReactiveComponent[A, B] with StrictLogging {
  def update(input: A): Future[B] = {
    logger.info(s"$processName started, input: $input")
    val rs = Await.ready(doUpdate(input), 3 seconds)
    logger.info(s"$processName completed, result: $rs")
    rs
  }

  def doUpdate(input: A): Future[B]
}

// extends Result[A, B]

trait KeyedInput {
  def key: String
}

trait StatefulComponent[A <: KeyedInput, B, M] extends ReactiveComponent[A, B] with StrictLogging {

  def update(model: Option[M], input: A): Future[(Option[M], B)] = {
    logger.info(s"$processName started, input: $input, model: $model")
    val rs = Await.ready(doUpdate(model, input), 3 seconds)
    logger.info(s"$processName completed, result: $rs")
    rs
  }

  def doUpdate(model: Option[M], input: A): Future[(Option[M], B)]

  def extract(model: String): M

  def modelSerialize(model: M): String

  //  def run(initModel: M, input: A): Future[B] = {
  //    runIt(initModel, input)
  //  }
  //
  //  def runIt(model: KeyedModel[K, M], input: A): Future[B] = {
  //    update(model, input).flatMap {
  //      case (newModel, result) => result match {
  //        case Output(output) => Future {
  //          output
  //        }
  //        case (FutureAction(futureAction)) =>
  //          futureAction.flatMap {
  //            newInput => runIt(newModel, newInput)
  //          }
  //      }
  //    }
  //  }
}

trait ComponentWithSideEffect[A, B] extends ReactiveComponent[A,B] with StrictLogging {
  def doUpdate(input: A): Result[A,B]
}

object ComponentWithSideEffect {

  trait Result[FutureInput, Output]

  case class FutureAction[A, B](futureAction: () => Future[A]) extends Result[A, B]

  case class Output[A, B](output: B)

}

