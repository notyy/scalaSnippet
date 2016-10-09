package compose
import basicFunctions.BasicFunctionModule._

object compose extends App {

  def compose[A, B, C](f1: A => B)(f2: B => C): A => C = x => f2(f1(x))

  import withLog.WithLogModule._
  withLog("add1thenMulti2")(compose(add1)(multi2))(2)
}
