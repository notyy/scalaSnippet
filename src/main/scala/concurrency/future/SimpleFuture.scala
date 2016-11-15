package concurrency.future

import com.typesafe.scalalogging.slf4j.StrictLogging

import scala.util.{Failure, Success, Try}

object SimpleFuture extends App with StrictLogging {
  def add(x: Int, y: Int): Int = x + y

  def concat(x: Int, y: Int): List[Int] = List(x, y)

  def divide(x: Int, y: Int): Option[Int] = if (y == 0) None else Some(x / y)

  def safeDiv(x: Int, y: Int): Try[Int] = Try {
    x / y
  }

  safeDiv(2,1).map(_ * 2).flatMap(i => safeDiv(i, 2)) match {
    case Success(v) => logger.info(s"result of div is $v")
    case Failure(e) => logger.info("failed to div", e)
  }
}
