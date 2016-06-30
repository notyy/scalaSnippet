package reactiveComponent

import reactiveComponent.framework.{Flow, SimpleTransformation}

object Platform {
  def run[A, B](simpleTransformation: SimpleTransformation[A, B], A: A): B = {
    simpleTransformation.update(A)
  }

  def run[A, B](flow: Flow[A, B], input: A): B = {
    flow.runThrough(input)
  }
}
