package concurrency.akka.cloc

import akka.actor.{ActorSystem, Props}
import com.typesafe.scalalogging.StrictLogging

object ClocApp extends App with StrictLogging {
  val system = ActorSystem("cloc")
  val collector = system.actorOf(Props[Collector],"collector")
  collector ! Collect("/Users/twer/source/data1")
}
