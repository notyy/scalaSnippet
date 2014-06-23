package expressiveness.typeclass

object ShapeModule {
  trait Shape

  case class Square(side: Double) extends Shape
  case class Circle(radius: Double) extends Shape

  trait HaveArea[T <: Shape]{
    def area(shape: T): Double
  }

  implicit object squareHaveArea extends HaveArea[Square] {
    override def area(square: Square): Double = square.side * square.side
  }
  implicit object circleHaveArea extends HaveArea[Circle] {
    override def area(circle: Circle): Double = 3.14 * circle.radius * circle.radius
  }

  trait HavePerimeter[T <: Shape]{
    def perimeter(shape: T): Double
  }

  implicit object squareHavePerimeter extends HavePerimeter[Square] {
    override def perimeter(square: Square): Double = square.side * 4
  }
}
