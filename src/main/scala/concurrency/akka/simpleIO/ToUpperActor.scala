package concurrency.akka.simpleIO

import akka.actor.{Actor, Props}
import concurrency.akka.simpleIO.ToUpperActor.{ToUpperReq, ToUpperResp}

object ToUpperActor {
  def props(): Props = Props(new ToUpperActor)

  case class ToUpperReq(value: String)
  case class ToUpperResp(value: String)
}

class ToUpperActor extends Actor {
  override def receive: Receive = {
    case ToUpperReq(v) => sender() ! ToUpperResp(v.toUpperCase)
  }
}
