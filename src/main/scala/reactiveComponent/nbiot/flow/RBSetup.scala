package reactiveComponent.nbiot.flow

import reactiveComponent.framework.SimpleTransformation
import reactiveComponent.nbiot.dependency.RBService
import reactiveComponent.nbiot.dependency.RBService.RBSetupResult
import reactiveComponent.nbiot.flow.RBSetup.RBSetupReq
import reactiveComponent.nbiot.flow.RRCProcessing.RRCInstance

import scala.concurrent.Future

class RBSetup extends SimpleTransformation[RBSetupReq, RBSetupResult] {


  override def processName: String = "RBSetup"

  override def doUpdate(input: RBSetupReq): Future[RBSetupResult] = {
    RBService.setupRB(input.rrcInstance.ueId, input.rrcInstance.cellId)
  }
}

object RBSetup {

  case class RBSetupReq(rrcInstance: RRCInstance)

  case class Success()

  case class Failure(reason: String)

}
