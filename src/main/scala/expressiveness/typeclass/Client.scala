package expressiveness.typeclass

import ShapeModule._

object Client {
  def printArea[T](shape: T)(implicit m: HaveArea[T]) = println(m.area(shape))
  def printPerimeter[T](shape: T)(implicit m: HavePerimeter[T]) = println(m.perimeter(shape))
}
