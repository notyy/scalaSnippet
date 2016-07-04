package reactiveComponent.nbiot.flow

import reactiveComponent.framework.{FutureAction, Output, Result, StatefulComponent}
import reactiveComponent.nbiot.flow.RRCRejectL2Notify._

import scala.concurrent.Future

class RRCRejectL2Notify(val rejectNotify: RRCConnReject => Future[RRCRejectL2Notify.L2Timeout]) extends StatefulComponent[Model, Input, Boolean] {
  override def update(model: Model, input: Input): (Model, Result[Input, Boolean]) = input match {
    case Reject(ueId) => {
      (model, FutureAction(rejectNotify(RRCConnReject(ueId))))
    }
    case (RRCRejectL2Notify.L2Timeout()) => {
      (model, Output(true))
    }
  }
}

object RRCRejectL2Notify {

  trait Input

  case class Reject(ueId: UeId) extends Input

  case class L2Timeout() extends Input

  case class RRCConnReject(ueId: UeId)

  case class Model(ueId: UeId, cellId: CellId)

}
