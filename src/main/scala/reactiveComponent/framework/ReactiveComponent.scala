package reactiveComponent.framework

import scala.concurrent.Future

trait ReactiveComponent[Input, Output]

trait SimpleTransformation[Input, Output] extends ReactiveComponent[Input, Output] {
  that =>

  def update(input: Input): Output

  def conn[Output1](nextSimpleTrans: SimpleTransformation[Output, Output1]): SimpleTransformation[Input, Output1] = {

    new SimpleTransformation[Input, Output1] {
      override def update(input: Input): Output1 = {
        val output: Output = that.update(input)
        nextSimpleTrans.update(output)
      }
    }
  }
}

trait StatefulComponent[Input, Model, Output] extends ReactiveComponent[Input, Output] {
  def init: Model

  def update(input: Input, model: Model): (Model, Option[Future[Input]], Output)
}

