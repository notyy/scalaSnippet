package reactiveComponent.nbiot.flow

import reactiveComponent.framework.SimpleTransformation
import reactiveComponent.nbiot.flow.RRCInstanceSetup.RBSetupReq
import reactiveComponent.nbiot.source.UL_CCCH_MSG

import scala.concurrent.Future

class RRCInstanceSetup extends SimpleTransformation[UL_CCCH_MSG, RBSetupReq]{
  override def update(input: UL_CCCH_MSG): Future[RBSetupReq] = ???
}

object RRCInstanceSetup {
  case class Model(ueId: UeId, rrcId: RRCInstanceId, cellId: CellId)
  case class RBSetupReq(mac: String)
}
