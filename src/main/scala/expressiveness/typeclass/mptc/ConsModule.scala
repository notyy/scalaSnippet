package expressiveness.typeclass.mptc

object ConsModule {

  trait Cons[A[_], B] {
    def cons(b: B,a:A[B]): A[B]

    def head(a: A[B]): B

    def tail(a:A[B]): A[B]

    def isEmpty(a: A[B]): Boolean
  }

  class ListIsCons[B] extends Cons[List, B] {
    override def cons(b: B,xs: List[B]): List[B] = b :: xs

    override def head(xs: List[B]): B = xs.head

    override def tail(xs:List[B]): List[B] = xs.tail

    override def isEmpty(xs: List[B]): Boolean = xs.isEmpty
  }
}

object ConsClient extends App {
  import ConsModule._

  def snd[A[_], B](a: A[B])(implicit c: Cons[A, B]): B = c.head(c.tail(a))

  implicit def lcons[A]: ListIsCons[A] = new ListIsCons[A]

  println(snd(List(1,2,3)))
}
