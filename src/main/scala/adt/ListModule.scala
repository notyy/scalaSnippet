package adt

object ListModule extends App {
  sealed trait MyList[+T]{
    def head: T
    def tail: MyList[T]
  }

  case object MyNil extends MyList[Nothing] {
    override def head: Nothing = throw new IllegalArgumentException("no head on empty List")

    override def tail: MyList[Nothing] = throw new IllegalArgumentException("no tail on empty List")
  }

  case class Cons[T](head: T, tail: MyList[T]) extends MyList[T]

  println(MyNil)
  val list: Cons[Int] = Cons(1, Cons(2, Cons(3, MyNil)))
  println(list)
  println(list.head)
  println(list.tail)
}
