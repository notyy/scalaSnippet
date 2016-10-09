package adt

import adt.ListModule.{Cons, MyList, MyNil}
import adt.TreeModule.{Branch, Leaf, Tree}

object FunctorModule {

  trait Functor[T[_]] {
    def map[A, B](t: T[A])(f: A => B): T[B]
  }

  implicit object FunctorMyTree extends Functor[Tree] {
    override def map[A, B](t: Tree[A])(f: (A) => B): Tree[B] = t match {
      case Leaf(v) => Leaf(f(v))
      case Branch(l,v,r) => Branch(map(l)(f), f(v), map(r)(f))
    }
  }

  implicit object FunctorMyList extends Functor[MyList] {
    override def map[A, B](t: MyList[A])(f: (A) => B): MyList[B] = t match {
      case MyNil => MyNil
      case Cons(h,tail) => Cons(f(h), map(tail)(f))
    }
  }
}


