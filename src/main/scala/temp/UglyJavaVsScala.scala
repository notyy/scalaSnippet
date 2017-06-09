package temp

object UglyJavaVsScala {
  def sum(xs: List[Int]): Int = xs.foldLeft(0)(_ + _)

  def sum1(xs: List[Int]): Int = xs.reduce(_ + _)

  def sum2(xs: List[Int]): Int = xs.sum

  def add(x:Int, y: Int): Int = x + y

  def sum3(xs: List[Int]): Int = xs.foldLeft(0)(add)
}