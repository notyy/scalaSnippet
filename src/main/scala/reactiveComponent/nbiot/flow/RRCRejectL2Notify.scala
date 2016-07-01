package reactiveComponent.nbiot.flow

import reactiveComponent.framework.StatefulComponent
import reactiveComponent.nbiot.flow.RRCRejectL2Notify.{Input, L2Input, Model, RRCConnReject}
import reactiveComponent.nbiot.source.UL_CCCH_MSG

import scala.concurrent.Future

class RRCRejectL2Notify(val rejectNotify: RRCConnReject => Future[RRCRejectL2Notify.L2Timeout]) extends StatefulComponent[Model, Input, Unit]{
  override def update(model: Model, input: Input): (Model, Option[Future[Input]], Option[Unit]) = input match {
    case L2Input(uL_CCCH_MSG) => uL_CCCH_MSG
  }
}

object RRCRejectL2Notify {
  trait Input
  case class L2Input(uL_CCCH_MSG: UL_CCCH_MSG) extends Input
  case class L2Timeout extends Input

  case class RRCConnReject(ueId: UeId)
  case class Model()
}
