package concurrency.akka.cloc

import akka.actor.{Props, ActorSystem}
import com.typesafe.scalalogging.slf4j.StrictLogging
import concurrency.akka.pingpang.{Pang, Ping}

object ClocApp extends App with StrictLogging {
  val system = ActorSystem("cloc")
  val collector = system.actorOf(Props[Collector],"collector")
  collector ! Collect("/Users/twer/source/data1")
}
