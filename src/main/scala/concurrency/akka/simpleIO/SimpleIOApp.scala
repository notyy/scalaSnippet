package concurrency.akka.simpleIO

import akka.actor.{ActorRef, ActorSystem}
import com.typesafe.scalalogging.StrictLogging
import concurrency.akka.simpleIO.MultiplyActor.NumberInput

import scala.io.StdIn

object SimpleIOApp extends App with StrictLogging {
  val system = ActorSystem("SimpleIOProcessing")
  val multiplyActor: ActorRef = system.actorOf(MultiplyActor.props())

  var shouldContinue = true
  try {
    while (shouldContinue) {
      logger.info("please input numbers to be processed, input :q to quit")
      val input = StdIn.readLine()
      if (input == ":q") {
        shouldContinue = false
      } else {
        multiplyActor ! NumberInput(input.toInt)
        //        implicit val timeout: Timeout = 5.seconds
        //        implicit val dispatcher =  system.dispatcher
        //        val rs = multiplyActor ? NumberInput(input.toInt)
//        rs.mapTo[String].foreach(v => logger.info(v))
//        logger.info(Await.result(rs.mapTo[String], 5 seconds))
      }
    }
  } finally {
    println("good bye!")
    system.terminate()
  }
}
