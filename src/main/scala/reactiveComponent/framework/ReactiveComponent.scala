package reactiveComponent.framework

import scala.concurrent.Future

trait ReactiveComponent[A, B]

trait SimpleTransformation[A, B] extends ReactiveComponent[A, B] {
  that =>

  def update(A: A): B

  def conn[C](nextSimpleTrans: SimpleTransformation[B, C]): SimpleTransformation[A, C] = {

    new SimpleTransformation[A, C] {
      override def update(A: A): C = {
        val B: B = that.update(A)
        nextSimpleTrans.update(B)
      }
    }
  }
}

trait StatefulComponent[A, Model, B] extends ReactiveComponent[A, B] {
  def init: Model

  def update(A: A, model: Model): (Model, Option[Future[A]], B)
}

