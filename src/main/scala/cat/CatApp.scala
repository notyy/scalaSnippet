package cat

import cats._
object CatApp extends App {
  def combineAll[A](list: List[A])(implicit a: Monoid[A]): A = list.foldRight(a.empty)(a.combine)

  implicit val intAdditionMonoid: Monoid[Int] = new Monoid[Int] {
    def empty: Int = 0
    def combine(x: Int, y: Int): Int = x + y
  }

  //have to comment this out, or the combine pair example will fail
//  implicit val intMultiMonoid: Monoid[Int] = new Monoid[Int] {
//    override def empty:Int = 1
//    override def combine(x: Int, y: Int) = x * y
//  }

  implicit val strAdditionMonoid: Monoid[String] = new Monoid[String] {
    def empty: String = ""
    def combine(x: String, y: String): String = x ++ y
  }

  implicit def pairMonoid[A,B](implicit a: Monoid[A], b: Monoid[B]): Monoid[(A,B)] =
    new Monoid[(A,B)] {
      override def empty: (A, B) = (a.empty, b.empty)

      override def combine(x: (A, B), y: (A, B)): (A, B) = {
        (a.combine(x._1, y._1), b.combine(x._2,y._2))
      }
    }

  println(combineAll(List(1,2,3,4,5))(intAdditionMonoid))
//  println(combineAll(List(1,2,3,4,5))(intMultiMonoid))
  println(combineAll(List("1","2","3")))
  println(combineAll(List((1,2),(3,4),(5,6))))
  println(combineAll(List((1,"2"),(3,"4"),(5,"6"))))
  println(combineAll(List(("1",2),("3",4),("5",6))))
  println(combineAll(List(("1","2"),("3","4"),("5","6"))))
}
