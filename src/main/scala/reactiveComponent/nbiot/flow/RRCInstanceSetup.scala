package reactiveComponent.nbiot.flow

import com.typesafe.scalalogging.slf4j.StrictLogging
import reactiveComponent.framework.SimpleTransformation
import reactiveComponent.nbiot.flow.RBSetup.RBSetupReq
import reactiveComponent.nbiot.source.UL_CCCH_MSG

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class RRCInstanceSetup extends SimpleTransformation[UL_CCCH_MSG, RBSetupReq] with StrictLogging {
  override def doUpdate(input: UL_CCCH_MSG): Future[RBSetupReq] = {
    logger.info("setup RRCInstance")
    val rs = Future(RBSetupReq(None))
    logger.info("setup RRCInstance complete")
    rs
  }

  override def processName: String = "RRCInstance"
}

object RRCInstanceSetup {

  case class Model(ueId: UeId, rrcId: RRCInstanceId, cellId: CellId)

}
