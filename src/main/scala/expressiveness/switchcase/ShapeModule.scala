package expressiveness.switchcase

object ShapeModule {

  trait Shape

  case class Square(side: Double) extends Shape
  case class Circle(radius: Double) extends Shape

  def area(shape: Shape):Double = shape match {
    case Square(side) => side * side
    case Circle(radius) => 3.14 * radius * radius
  }
}