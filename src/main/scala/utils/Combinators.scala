package utils

import scala.io._
import java.io._

object Combinators {
  type Line = String

  def doOver(inputFile: String, outputFile: String)(f: Line => Line): Unit = {
    doOver(Source.fromFile(inputFile).getLines())(f) { rs =>
      val pw = new PrintWriter(outputFile)
      pw.write(rs.mkString("\n"))
      pw.close()
    }
  }

  def doOver[A, B](in: => Iterator[A])(f: A => B)(out: Iterator[B] => Unit): Unit = {
    out(in.map(f))
  }
}
