package reactiveComponent.framework

import scala.concurrent.Future

trait ReactiveComponent[Input, Model, Output]

trait SimpleTransformation[Input, Model, Output] extends ReactiveComponent[Input, Model, Output] {
  def update(input: Input): (Option[Future[Input]], Output)
}

trait StatefulComponent[Input, Model, Output] extends ReactiveComponent[Input, Model, Output] {
  def init: Model

  def update(input: Input, model: Model): (Model, Option[Future[Input]], Output)
}

