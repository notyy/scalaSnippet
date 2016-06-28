package concurrency.akka.account

import akka.actor.{ActorSystem, PoisonPill, Props}
import com.typesafe.scalalogging.slf4j.StrictLogging

object AccountApp extends App with StrictLogging {
  val system = ActorSystem("accountSystem")
  val agent1 = system.actorOf(Props[AccountAssistant], "agent1")
  logger.info("start")
  agent1 ! Create("xx", 50)
  agent1 ! Create("yy", 150)
  agent1 ! Create("zz", 250)
  agent1 ! Increase("xx", 20)
  agent1 ! Decrease("xx", 10)
  agent1 ! Query("xx")
  agent1 ! Report
  agent1 ! PoisonPill
  Thread.sleep(1000)
  system.terminate()
}
