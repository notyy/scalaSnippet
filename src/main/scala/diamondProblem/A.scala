package diamondProblem

trait A{
  def hello() = println("hello from A")
}

trait B extends A{
  override def hello() = println("hello from B")
}

trait C extends A {
  override def hello() = println("hello from C")
}

object D extends App with B with C{
  hello()
}
