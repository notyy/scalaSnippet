package reactiveComponent.nbiot.flow

import reactiveComponent.Platform
import reactiveComponent.framework.{Output, Result, StatefulComponent}
import reactiveComponent.nbiot.flow.CellLoadBalanceCheck.CellUECount
import reactiveComponent.nbiot.flow.RRCProcessing.{Input, RRCConnReq, RRCInstance}
import reactiveComponent.nbiot.source.UL_CCCH_MSG

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

import scala.util.{Failure, Success}

class RRCProcessing extends StatefulComponent[Option[RRCInstance], Input, Option[RRCInstance]] {

  override def processName: String = "RRCProcess"

  override def doUpdate(model: Option[RRCInstance], input: Input): Future[(Option[RRCInstance], Result[Input, Option[RRCInstance]])] =
    input match {
      case RRCConnReq(ulccchmsg) => {
        Platform.run(new RRCConnectionSetup)(ulccchmsg, CellUECount(3)).map {
          case Success(rRCInstance) => (Some(rRCInstance), Output(None))
          case Failure(e) => {
            e.printStackTrace()
            (None, Output(None))
          }
        }
      }
    }
}

object RRCProcessing {

  //Model
  case class RRCInstance(ueId: UeId, rrcConnReason: String, cellId: CellId, coverRate: Double)

  //Input
  trait Input

  case class RRCConnReq(uL_CCCH_MSG: UL_CCCH_MSG) extends Input

  case class NasTransport(ueId: UeId, cellId: CellId, content: String) extends Input

  case class RRCRelease(ueId: UeId, cellId: CellId) extends Input

}
