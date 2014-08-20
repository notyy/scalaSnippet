package patterns

trait Persist[String] {
  def get: String

  def map(f: String => String): Persist[String]
}

object Persist {

  def apply(path: Path, content: String): Persist[String] = ???

  implicit class PoliteInt(val value: Int) {
    def sayHello() = println(s"hello, from $value")
  }

  1.sayHello()
}
