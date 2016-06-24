package funcserv.akka

import akka.actor.{ActorRef, Actor}
import akka.actor.Actor.Receive
import funcserv.akka.HSS.{GetAuthVectorResp, GetAuthVector}
import funcserv.akka.MME.{AuthRequestNoImsi, AuthRequest}
import funcserv.akka.UE.{GetImsi, UEAuth}

class MME(hss: ActorRef, ue: ActorRef) extends Actor{
  
  override def receive: Receive = waitAttachRequest

  def waitAttachRequest: Receive = {
    case AuthRequestNoImsi => {
      ue ! GetImsi
      context become waitIMSI
    }
    case AuthRequest(imsi) => {
      hss ! GetAuthVector(imsi)
      context become waitGetAuthVectorResp
    }
  }
  
  def waitGetAuthVectorResp: Receive = {
    case GetAuthVectorResp(imsi) => {
      ue ! UEAuth(imsi)
      context become waitUEAuthResp
    }
  }

  def waitUEAuthResp: Receive = ???
  def waitIMSI: Receive = ???
}

object MME{
  case class AuthRequest(imsi: String)
  case object AuthRequestNoImsi
  
}
