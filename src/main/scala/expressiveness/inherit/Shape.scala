package expressiveness.inherit

trait Shape {
  def area:Double
}

class Square(side:Double) extends Shape {
  override def area: Double = side * side

}

class Circle(radius: Double) extends Shape {
  override def area: Double = 3.14 * radius * radius
}

