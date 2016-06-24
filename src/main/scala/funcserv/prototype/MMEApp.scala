package funcserv.prototype

import funcserv.prototype.UE.{ConnectionFailed, ConnectionOK}

import scala.concurrent.ExecutionContext.Implicits.global


object MMEApp extends App {
  //  UE.createConnectionWithImsi("ue0001", "imsi0001").onSuccess {
  //    case ConnectionOK(ueID) => println(s"$ueID successfully connected")
  //    case ConnectionFailure(ueID, cause) => println(s"$ueID failed to connect because $cause")
  //  }

  println("-------------------sep-------------------")
  val conn = UE.createConnectionNoImsi("ue0001")
  conn.onSuccess {
    case ConnectionOK(ueID) => println(s"$ueID successfully connected")
    case ConnectionFailed(ueID, cause) => println(s"$ueID failed to connect because $cause")
  }
  conn.onFailure { case ex: Throwable => println(s"error occurs:${ex.getMessage}") }

  Thread.sleep(1000)
}
