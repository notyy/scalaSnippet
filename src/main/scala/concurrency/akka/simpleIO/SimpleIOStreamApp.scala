package concurrency.akka.simpleIO

import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, OverflowStrategy, ThrottleMode}
import akka.stream.scaladsl.{Flow, Sink, Source}
import akka.{Done, NotUsed}
import com.typesafe.scalalogging.slf4j.StrictLogging

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.io.StdIn

object SimpleIOStreamApp extends App with StrictLogging {
  implicit val system = ActorSystem("CodebaseAnalyzer")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher

  logger.info("please input numbers to be processed")

  private def printResult(s: String) = {
    logger.info(s)
    logger.info("please input numbers to be processed")
  }

  //  Source.fromIterator(() => Iterator.continually(StdIn.readLine()))
  //    .map(_.toInt * 2).map(v => s"hello, your result is $v")
  //    .runForeach(printResult)
  //
  val source: Source[String, NotUsed] = Source.fromIterator(() => Iterator.continually(StdIn.readLine()))
  val flow: Flow[String, String, NotUsed] =
    Flow[String].buffer(1, OverflowStrategy.backpressure)
        .throttle(1, 1 second,1, ThrottleMode.shaping)
      .map{ i => i.toInt * 2}.map { v =>
    s"hello, your result is $v"
  }
  val sink: Sink[String, Future[Done]] = Sink.foreach[String]{ rs =>
    printResult(rs)
  }
  //
  val listSource = Source.fromIterator(() => (1 to 100).map(_.toString).toIterator)
  val tickSource = Source.tick(0 second, 200 millis, 0)
  val source1 = listSource.zip(tickSource).map(_._1).buffer(3, OverflowStrategy.dropNew)
  //
  source1.runWith(flow to sink)
}
