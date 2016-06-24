package funcserv.akka

import akka.actor.Actor
import akka.actor.Actor.Receive

class HSS extends Actor{
  override def receive: Receive = ???
}

object HSS {
  case class GetAuthVector(imsi: String)
  case class GetAuthVectorResp(imsi: String)
}
