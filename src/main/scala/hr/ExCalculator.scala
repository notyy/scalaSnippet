package hr

import scala.annotation.tailrec

object ExCalculator {
  def f(x: Float): Float = ff(BigDecimal(x),9).toFloat

  def ff(x: BigDecimal, n: Int): BigDecimal = n match{
    case 0 => 1
    case i => (times(x, n) / factorial(n)) + ff(x, n-1)
  }

  def times(x:BigDecimal, n: Int) = List.fill(n)(x).product

  def factorial(n:Int): Int = n match {
    case 1 => 1
    case x => x * factorial(x - 1)
  }
}
