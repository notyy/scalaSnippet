package fold

object fold extends App{
  def fold[A,B](xs: List[A])(acc: B)(f: (B,A) => B): B = {
    var rs = acc
    for(i <- xs) rs = f(rs,i)
    rs
  }

  def reduce[A](xs: List[A])(f: (A,A) => A): A = fold(xs.tail)(xs.head)(f)

  def reverse[A](xs: List[A]): List[A] = fold(xs)(List[A]())((acc, i) => i :: acc)

  val l = List(1,2,3)
  println(s"reverse of $l is ${reverse(l)}")

  def sum: List[Int] => Int = reduce(_)(_ + _)
  def product: List[Int] => Int = reduce(_)(_ * _)

  println(s"sum = ${sum(List(3,4,5))}")
  println(s"product = ${product(List(3,4,5))}")


  val conditions = List("xx = 1","yy > 2","zz < 3")
  def concat: List[String] => String = reduce(_)((acc,i) => s"$acc AND $i")
  println(s"SELECT * FROM user WHERE ${concat(conditions)}")

//  @annotation.tailrec
//  def foldLeft[A, B](l: List[A], z: B)(f: (B, A) => B): B = l match {
//    case Nil => z
//    case Cons(h, t) => foldLeft(t, f(z, h))(f)
//  }
//
//  def foldRight[A, B](l: List[A], z: B)(f: (A, B) => B): B =
//    foldLeft(l, (b: B) => b)((g, a) => b => g(f(a, b)))(z)
}
