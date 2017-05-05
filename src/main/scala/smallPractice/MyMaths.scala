package smallPractice

import scala.annotation.tailrec

object MyMaths {
  @tailrec
  def maxDiviser(x: Int, y: Int): Int = {
    if (x == y) x
    else maxDiviser(math.abs(x - y), math.min(x, y))
  }
}
