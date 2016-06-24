package funcserv.prototype

import funcserv.prototype.MME.AuthRequest

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


object UE {
  trait ConnectionStatus
  case class ConnectionOK(ueID: String) extends ConnectionStatus
  case class ConnectionFailed(ueID: String, cause: String) extends ConnectionStatus

  def createConnectionWithImsi(ueID: String, imsi: String): Future[ConnectionStatus] = {
    println(s"creating connection: ueID = $ueID, imsi = $imsi")
    MME.processAuthReq(AuthRequest(ueID, Some(imsi))).map(_ => ConnectionOK(ueID))
  }

  def createConnectionNoImsi(ueID: String): Future[ConnectionStatus] = {
    println(s"creating connection without imsi: ueID = $ueID")
    MME.processAuthReq(AuthRequest(ueID, None)).map(_ => ConnectionOK(ueID))
  }

  def getImsi(ueID: String): Future[String] = Future{
    println(s"getImsi for ueID: $ueID")
    "imsi"+ueID.drop(2)
//    throw new IllegalStateException(s"GET IMSI ERROR for ueID:$ueID")
  }

}
