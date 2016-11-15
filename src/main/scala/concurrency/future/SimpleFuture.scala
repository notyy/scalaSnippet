package concurrency.future

import com.typesafe.scalalogging.slf4j.StrictLogging

import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success, Try}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object SimpleFuture extends App with StrictLogging {
  def add(x: Int, y: Int): Int = x + y

  def concat(x: Int, y: Int): List[Int] = List(x, y)

  def divide(x: Int, y: Int): Option[Int] = if (y == 0) None else Some(x / y)

  def safeDiv(x: Int, y: Int): Try[Int] = Try {
    x / y
  }

//  safeDiv(2,1).map(_ * 2).flatMap(i => safeDiv(i, 2)) match {
//    case Success(v) => logger.info(s"result of div is $v")
//    case Failure(e) => logger.info("failed to div", e)
//  }

  def sum(xs: List[Int]): Future[Int] = Future {
    logger.info("summing")
    Thread.sleep(1000)
    xs.sum
  }

  logger.info("starting...")
  val futureSum = sum(List(1,2,3))
  futureSum.onSuccess{
    case i => logger.info(s"sum result is $i")
  }
  futureSum.onFailure{
    case t => logger.info(s"sum failed", t)
  }
  Thread.sleep(2000)
  logger.info("done")
}
