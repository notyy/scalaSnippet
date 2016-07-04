package reactiveComponent.framework

import com.typesafe.scalalogging.slf4j.StrictLogging

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait ReactiveComponent[A, B] {
  def processName: String
}

trait SimpleTransformation[A, B] extends ReactiveComponent[A, B] with StrictLogging {
  def update(input: A): Future[B] = {
    logger.info(s"$processName started")
    val rs = doUpdate(input)
    logger.info(s"$processName completed")
    rs
  }

  def doUpdate(input: A): Future[B]
}

trait Result[Input, Output]

case class FutureAction[A, B](futureAction: Future[A]) extends Result[A, B]

case class Output[A, B](output: B) extends Result[A, B]

trait StatefulComponent[Model, A, B] extends ReactiveComponent[A, B] with StrictLogging {

  def update(model: Model, input: A): (Model, Result[A, B]) = {
    logger.info(s"$processName started")
    val rs = doUpdate(model, input)
    logger.info(s"$processName completed")
    rs
  }

  def doUpdate(model: Model, input: A): (Model, Result[A, B])

  def run(initModel: Model, input: A): Future[B] = {
    runIt(initModel, input)
  }

  def runIt(model: Model, input: A): Future[B] = {
    val (newModel, result) = update(model, input)
    result match {
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

