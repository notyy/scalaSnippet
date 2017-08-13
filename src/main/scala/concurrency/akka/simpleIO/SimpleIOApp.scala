package concurrency.akka.simpleIO

import akka.actor.{ActorRef, ActorSystem}
import com.typesafe.scalalogging.slf4j.StrictLogging
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
      }
    }
  } finally {
    println("good bye!")
    system.terminate()
  }
}
