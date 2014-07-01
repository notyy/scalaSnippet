package concurrentcy

object Concurrency {
  def sum(xs: List[Int]): Int = xs match {
    case Nil => 0
    case (x :: Nil) => x
    case _ => {
      val (l,r) = xs.splitAt(xs.length / 2)
      sum(l) + sum(r)
    }
  }
}
