package cat

import cats._
object CatApp extends App {
  def combineAll[A](list: List[A])(implicit a: Monoid[A]): A = list.foldRight(a.empty)(a.combine)

  implicit val intAdditionMonoid: Monoid[Int] = new Monoid[Int] {
    def empty: Int = 0
    def combine(x: Int, y: Int): Int = x + y
  }

  implicit val intMultiMonoid: Monoid[Int] = new Monoid[Int] {
    override def empty:Int = 1
    override def combine(x: Int, y: Int) = x * y
  }

  implicit val strAdditionMonoid: Monoid[String] = new Monoid[String] {
    def empty: String = ""
    def combine(x: String, y: String): String = x ++ y
  }

  println(combineAll(List(1,2,3,4,5))(intAdditionMonoid))
  println(combineAll(List(1,2,3,4,5))(intMultiMonoid))
  println(combineAll(List("1","2","3")))
}
