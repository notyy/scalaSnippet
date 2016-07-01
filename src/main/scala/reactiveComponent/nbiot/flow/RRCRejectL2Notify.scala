package reactiveComponent.nbiot.flow

import reactiveComponent.framework.{FutureAction, Output, Result, StatefulComponent}
import reactiveComponent.nbiot.flow.RRCRejectL2Notify._
import reactiveComponent.nbiot.source.UL_CCCH_MSG

import scala.concurrent.Future

class RRCRejectL2Notify(val rejectNotify: RRCConnReject => Future[RRCRejectL2Notify.L2Timeout]) extends StatefulComponent[EmptyModel, Input, Boolean] {
  override def update(model: EmptyModel, input: Input): (EmptyModel, Result[Input, Boolean]) = input match {
    case L2Input(uL_CCCH_MSG) => {
      (model, FutureAction(rejectNotify(RRCConnReject(uL_CCCH_MSG.ueId))))
    }
    case (RRCRejectL2Notify.L2Timeout()) => {
      (model, Output(true))
    }
  }
}

object RRCRejectL2Notify {

  trait Input

  case class L2Input(uL_CCCH_MSG: UL_CCCH_MSG) extends Input

  case class L2Timeout() extends Input

  case class RRCConnReject(ueId: UeId)

  case class EmptyModel()

}
