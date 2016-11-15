package concurrency.future

object SimpleFuture {
  def add(x: Int, y: Int): Int = x + y

  def concat(x: Int, y: Int): List[Int] = List(x, y)

  def divide(x: Int, y: Int): Option[Int] = if (y == 0) None else Some(x / y)

  def sum(xs: List[Int]): Future[Int] = ???
}
