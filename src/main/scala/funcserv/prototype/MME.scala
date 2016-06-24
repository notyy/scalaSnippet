package funcserv.prototype

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


object MME {

  case class AuthRequest(ueID: String, imsi: Option[String])

  case class AuthResponse(ueID: String)

  def processAuthReq(authRequest: AuthRequest): Future[AuthResponse] = {
    println(s"processingAuthRequest $authRequest")
    for {
      i <- if (authRequest.imsi.isEmpty) {
        UE.getImsi(authRequest.ueID)
      } else Future {
        authRequest.imsi.get
      }
      av <- HSS.getAuthVector(authRequest.ueID, i)
    } yield AuthResponse(av.ueID)
    //    fImsi.flatMap(i => HSS.getAuthVector(authRequest.ueID, i)).map(av => AuthResponse(av.ueID))
  }
}

