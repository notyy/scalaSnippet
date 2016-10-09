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
      log.info("Pang! received")
      count += 1
      if (count < 10) sender() ! "Ping!"
      else context become tired
    }
  }

  def tired: Receive = {
    case _ => {
      log.info("already tried")
    }
  }
}
