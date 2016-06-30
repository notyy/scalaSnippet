package reactiveComponent.framework

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait ReactiveComponent[A, B]

trait SimpleTransformation[A, B] extends ReactiveComponent[A, B] {

  def update(input: A): B
}

trait StatefulComponent[Model, A, B] extends ReactiveComponent[A, B] {

  def update(model: Model, A: A): (Model, Option[Future[A]], Option[B])

  def run(initModel: Model, input: A): Future[B] = {
    runIt(initModel, input)
  }

  def runIt(model: Model, input: A): Future[B] = {
    val (newModel, optFutureInput, optOutput) = update(model, input)
    (optFutureInput, optOutput) match {
      case (None, None) => throw new IllegalStateException("future input and output can't both be empty")
      case (None, Some(output)) => Future {
        output
      }
      case (Some(futureInput), Some(_)) => throw new IllegalArgumentException("there are future input to be processed, output will not ignored!!!!")
      case (Some(futureInput), _) =>
        futureInput.flatMap {
          newInput => runIt(newModel, newInput)
        }
    }
  }
}

