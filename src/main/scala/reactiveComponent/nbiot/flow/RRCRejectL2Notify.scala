package reactiveComponent.nbiot.flow

import reactiveComponent.framework.{FutureAction, Output, Result, StatefulComponent}
import reactiveComponent.nbiot.flow.RRCRejectL2Notify._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class RRCRejectL2Notify(val rejectNotify: RRCConnReject => Future[RRCRejectL2Notify.L2Timeout]) extends StatefulComponent[Model, Input, Boolean] {
  override def doUpdate(model: Model, input: Input): Future[(Model, Result[Input, Boolean])] = input match {
    case Reject(ueId) => {
      Future {
        (model, FutureAction(rejectNotify(RRCConnReject(ueId))))
      }
    }
    case (RRCRejectL2Notify.L2Timeout()) => {
      Future {
        (model, Output(true))
      }
    }
  }

  override def processName: String = "RRCRejectL2Notify"
}

object RRCRejectL2Notify {

  trait Input

  case class Reject(ueId: UeId) extends Input

  case class L2Timeout() extends Input

  case class RRCConnReject(ueId: UeId)

  case class Model(ueId: UeId, cellId: CellId)

}
