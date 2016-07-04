package reactiveComponent

import reactiveComponent.framework.{Flow, SimpleTransformation, StatefulComponent}

import scala.concurrent.Future

object Platform {
  def run[A, B](simpleTransformation: SimpleTransformation[A, B])(input: A): Future[B] = {
    simpleTransformation.update(input)
  }

  def run[A, B](flow: Flow[A, B], input: A): Future[B] = {
    flow.runThrough(input)
  }

  def run[Model,A,B]( statefulComponent: => StatefulComponent[Model, A,B])(input: A, model: Model): Future[B] = {
    statefulComponent.run(model, input)
  }
}
