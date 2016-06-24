package concurrency.akka.pingpang

import akka.actor.{Actor, ActorLogging}

class Pang extends Actor with ActorLogging {
  override def receive: Receive = {
    case "Ping!" => {
      log.info("Ping! received")
      sender() ! "Pang!"
    }
  }
}
