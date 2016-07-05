package reactiveComponent.framework

import com.typesafe.scalalogging.slf4j.StrictLogging

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
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

trait Result[FutureInput, Output]

case class FutureAction[A, B](futureAction: Future[A]) extends Result[A, B]

case class Output[A, B](output: B) extends Result[A, B]

trait StatefulComponent[Model, A, B] extends ReactiveComponent[A, B] with StrictLogging {

  def update(model: Model, input: A): Future[(Model, Result[A, B])] = {
    logger.info(s"$processName started, input: $input, model: $model")
    val rs = Await.ready(doUpdate(model, input), 3 seconds)
    logger.info(s"$processName completed, result: $rs")
    rs
  }

  def doUpdate(model: Model, input: A): Future[(Model, Result[A, B])]

  def run(initModel: Model, input: A): Future[B] = {
    runIt(initModel, input)
  }

  def runIt(model: Model, input: A): Future[B] = {
    update(model, input).flatMap {
      case (newModel, result) => result match {
        case Output(output) => Future {
          output
        }
        case (FutureAction(futureAction)) =>
          futureAction.flatMap {
            newInput => runIt(newModel, newInput)
          }
      }
    }
  }
}

