package expressiveness.typeclass

object ShapeModule {
  case class Square(side: Double)
  case class Circle(radius: Double)

  trait HaveArea[T]{
    def area(shape: T): Double
  }

  implicit object squareHaveArea extends HaveArea[Square] {
    override def area(square: Square): Double = square.side * square.side
  }
  implicit object circleHaveArea extends HaveArea[Circle] {
    override def area(circle: Circle): Double = 3.14 * circle.radius * circle.radius
  }

  trait HavePerimeter[T]{
    def perimeter(shape: T): Double
  }

  implicit object squareHavePerimeter extends HavePerimeter[Square] {
    override def perimeter(square: Square): Double = square.side * 4
  }
}
