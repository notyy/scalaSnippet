package concurrency.futurePractice

import com.typesafe.scalalogging.StrictLogging

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.io.StdIn
import scala.util.{Failure, Success, Try}

object FuturePractice extends App with StrictLogging {
  def greeting: String = {
    logger.info("creating greeting")
    "hello,world"
  }

  def greetingFuture: Future[String] = Future {
//    throw new IllegalStateException("BAD BAD")
    logger.info("creating greeting")
    "hello,world from future"
  }

  logger.info("let's begin")
  //  logger.info(s"i say $greetingFuture")
  val lastFuture = greetingFuture.flatMap { s =>
    Future {
      throw new IllegalArgumentException("xx wrong")
      logger.info("creating xx")
      s + "-xx"
    }
  }.map { s =>
      logger.info("creating yy")
      s + "-yy"
  }
//  logger.info(s"i say: ${Await.result(lastFuture, 2 seconds)}")
  //  greetingFuture.foreach(s => logger.info(s"i say: $s"))
  lastFuture.onComplete{
    case Success(x) => logger.info(x)
    case Failure(e) => logger.error(e.getMessage,e)
  }
  logger.info("something")

  def processResult: Try[String] => String = {
    case Success(s) => logger.info(s"i say: $s"); "x"
    case Failure(e) => logger.error(s"error occures: ${e.getMessage}", e); "y"
  }

  println("press enter to close")
  StdIn.readLine()
}

