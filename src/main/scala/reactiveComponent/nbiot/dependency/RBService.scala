package reactiveComponent.nbiot.dependency

import reactiveComponent.nbiot.flow._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


object RBService {

  def setupRB(ueId: UeId, cellId: CellId): Future[RBSetupResult] = Future {
    RBSetupResult(RLC("rlc"), PDCP("pdcp"), Mac("mac"), ueId, cellId)
  }

  case class RBSetupResult(rlc: RLC, pdcp: PDCP, mac: Mac, ueId: UeId, cellId: CellId)

}
