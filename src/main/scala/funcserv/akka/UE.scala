package funcserv.akka

import akka.actor.Actor
import akka.actor.Actor.Receive

class UE extends Actor{
  override def receive: Receive = ???
}

object UE {
  case class UEAuth(imsi: String)
  case class UEAuthResp(imsi: String)
  case object GetImsi
}
