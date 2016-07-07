package reactiveComponent.nbiot.flow

import reactiveComponent.framework.SimpleTransformation
import reactiveComponent.nbiot.flow.RRCProcessing.RRCInstance
import reactiveComponent.nbiot.source.UL_CCCH_MSG

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class RRCInstanceSetup extends SimpleTransformation[UL_CCCH_MSG, RRCInstance] {
  override def doUpdate(input: UL_CCCH_MSG): Future[RRCInstance] = {
    Future(RRCInstance(input.ueId, "some reason", input.cellId,0.50))
  }

  override def processName: String = "RRCInstanceSetup"
}

object RRCInstanceSetup {


}
