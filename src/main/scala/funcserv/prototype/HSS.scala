package funcserv.prototype

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object HSS {

  case class AuthVector(ueID: String)

  def getAuthVector(ueID: String, imsi: String): Future[AuthVector] = Future {
    println(s"getAuthVector for ueID: $ueID, imsi: $imsi")
    AuthVector(ueID)
    throw new IllegalStateException("Get AuthVector Error!")
  }
}
