package concurrency.akka.cloc

import akka.actor.{Actor, ActorLogging}

import scala.io.Source

case class Count(path: String)

class Counter extends Actor with ActorLogging{
  override def receive: Receive = {
    case Count(path) => {
      val loc = Source.fromFile(path).getLines().length
      sender() ! Report(path, loc)
    }
  }
}
