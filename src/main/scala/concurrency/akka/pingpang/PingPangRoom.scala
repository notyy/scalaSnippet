package concurrency.akka.pingpang

import akka.actor.{ActorSystem, PoisonPill, Props}
import com.typesafe.scalalogging.slf4j.StrictLogging

object PingPangRoom extends App with StrictLogging {
  val system = ActorSystem("pingpang")
  val ping = system.actorOf(Props[Ping], "ping")
  val pang = system.actorOf(Props[Pang], "pang")
  ping ! Start(pang)
  logger.info("watching")
  Thread.sleep(2000)
  system.shutdown()
}
