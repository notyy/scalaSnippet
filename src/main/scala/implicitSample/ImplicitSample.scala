package implicitSample

object ImplicitSample extends App {
  implicit class PoliteInt(value: Int) {
    def sayHello():Unit = println(s"hello, I am $value")
  }

  42.sayHello()
}

