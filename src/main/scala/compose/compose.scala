package compose

object compose extends App {

  def add1: Int => Int = _ + 1

  def multi2: Int => Int = _ * 2

  def int2String: Int => String = _.toString

  def add1ThenMulti2: Int => Int = x => multi2(add1(x))

  def compose[A, B, C](f1: A => B)(f2: B => C): A => C = x => f2(f1(x))

  import withLog.WithLogModule._
  withLog("add1thenMulti2")(compose(add1)(multi2))(2)
}
