package concurrency.akka.cloc

import java.io.File

import akka.actor.Actor.Receive
import akka.actor.{Props, ActorLogging, Actor}
import concurrency.akka.pingpang.Ping

case class Collect(directory: String)
case class Report(filePath: String, loc: Int)

class Collector extends Actor with ActorLogging {

  override def receive: Receive = {
    case Collect(dir) => {
      val files = new File(dir).listFiles().filter(_.isFile)
      files.foreach(file => {
        context.actorOf(Props[Counter],file.getName) ! Count(file.getPath)
      })
    }
    case Report(filePath, loc) => log.info(s"$filePath has $loc lines of code")
  }
}
