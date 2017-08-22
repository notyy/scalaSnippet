package concurrency.akka.simpleIO

import akka.actor.{Actor, Props}
import com.typesafe.scalalogging.slf4j.StrictLogging
import concurrency.akka.simpleIO.GreetingActor.{GreetResp, GreetingReq}

object GreetingActor {
  def props():Props = Props(new GreetingActor)

  case class GreetingReq(value: String)
  case class GreetResp(value: String)
}

class GreetingActor extends Actor with StrictLogging {

  override def preStart(): Unit = {
    logger.info("restarting")
  }

  override def receive: Receive = {
    case GreetingReq(v) => {
      if(v == "222") {
        throw new IllegalArgumentException("can't process 222")
      } else
        sender() ! GreetResp(s"hello, your result is $v")
    }
  }
}
