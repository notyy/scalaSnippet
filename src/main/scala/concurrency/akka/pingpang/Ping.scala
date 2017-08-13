package concurrency.akka.pingpang

import akka.actor.{ActorRef, ActorLogging, Actor}

case class Start(opponent: ActorRef)

class Ping extends Actor with ActorLogging {
  private var count = 0

  override def receive: Receive = {
    case Start(opponent) => {
      log.info("starting...")
      opponent ! "Ping!"
    }
    case "Pang!" => {
      count += 1
      log.info(s"Pang! received $count times")
      if (count >= 10) {
        context become tired
      }
      sender() ! "Ping!"
    }
  }

  def tired: Receive = {
    case _ => {
      log.info("already tired")
    }
  }
}
