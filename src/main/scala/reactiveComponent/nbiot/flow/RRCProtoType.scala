package reactiveComponent.nbiot.flow

import reactiveComponent.nbiot.dependency.RBService.RBSetupResult
import reactiveComponent.nbiot.flow.RBSetup.RBSetupReq
import reactiveComponent.nbiot.flow.RRCProcessing.RRCInstance
import reactiveComponent.nbiot.source.UL_CCCH_MSG

import scala.util.Try

object RRCProtoType extends App {

  case class RRCConnReq(uL_CCCH_MSG: UL_CCCH_MSG)

  case class MT_TCL2_DL_CCCH_MSG(ueId: UeId, cellId: CellId)

  def processRRCConnectionReq: RRCConnReq => Try[(RRCInstance, MT_TCL2_DL_CCCH_MSG)] = rrcConnReq => {
    val uL_CCCH_MSG = rrcConnReq.uL_CCCH_MSG
    val ueId = uL_CCCH_MSG.ueId
    val cellId = uL_CCCH_MSG.cellId

    if (cellLBCheck(ueId, cellId)) {
      rrcInstanceSetup(uL_CCCH_MSG).flatMap { rrcInstance =>
        rbSetup(RBSetupReq(rrcInstance)).map { rbSetupResult =>
          val mT_TCL2_DL_CCCH_MSG = constructRRCConnectionSetupMsg(ueId, cellId, rbSetupResult)
          (rrcInstance, mT_TCL2_DL_CCCH_MSG)
        }
      }
    } else {
      rrcConnReject(ueId, cellId).map { _ =>
        throw new IllegalStateException("exceed load capacity")
      }
    }
  }

  def cellLBCheck: (UeId, CellId) => Boolean = (_, _) => true

  def rrcConnReject: (UeId, CellId) => Try[Unit] = (_, _) => Try(())

  def rrcInstanceSetup: UL_CCCH_MSG => Try[RRCInstance] = ul_ccch_msg => Try {
    RRCInstance(ul_ccch_msg.ueId, "some reason", ul_ccch_msg.cellId, 0.5)
//    throw new IllegalArgumentException("can't setup rrcInstance")
  }

  def rbSetup: RBSetupReq => Try[RBSetupResult] = rbSetupReq => Try {
    val rRCInstance = rbSetupReq.rrcInstance
    RBSetupResult(RLC("rlc"), PDCP("pdcp"), Mac("mac"), rRCInstance.ueId, rRCInstance.cellId)
  }

  def constructRRCConnectionSetupMsg: (UeId, CellId, RBSetupResult) => MT_TCL2_DL_CCCH_MSG = (u, c, rr) => MT_TCL2_DL_CCCH_MSG(u, c)

  val processResult = processRRCConnectionReq(RRCConnReq(UL_CCCH_MSG("content", CellId("cell001"), UeId("ue0001"))))
  println(processResult)
}
