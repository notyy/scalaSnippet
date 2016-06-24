package fpbasic

object MyFunctor {
  def map[A,B](xs: List[A], f: A => B): List[B] = xs match {
    case Nil => Nil
    case (h :: t) => f(h) :: map(t, f)
  }

  def filter[A](xs: List[A], f: A => Boolean): List[A] = xs match {
    case Nil => Nil
    case (h :: t) => if(f(h)) h :: filter(t, f) else filter(t,f)
  }
}
