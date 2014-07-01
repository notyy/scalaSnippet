package optionSample

object OptionFuncs {
  def map2[A,B,C](a: Option[A], b: Option[B])(f: (A,B) => C): Option[C] = {
    a.flatMap(x => b.map(y => f(x,y)))
  }


  def sequence[A](a : List[Option[A]]): Option[List[A]] = {
    a.foldLeft(Option(List[A]()))((acc,i) => map2(acc,i)((x,y) => x :+ y))
  }
}
