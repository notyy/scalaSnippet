package concurrency.future

import com.typesafe.scalalogging.StrictLogging

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.language.postfixOps
import scala.concurrent.ExecutionContext.Implicits.global

object FutureAwait extends App with StrictLogging {
  logger.info("now")

  def doSomethingInFuture: Future[Int] = Future {
    println(s"this is in thread: ${Thread.currentThread().getName}")
    1
  }

  val rs = Await.result(doSomethingInFuture, 1 second)
  logger.info(s"rs is $rs")
}
