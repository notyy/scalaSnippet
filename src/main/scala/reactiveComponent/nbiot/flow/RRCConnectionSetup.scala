package reactiveComponent.nbiot.flow

import reactiveComponent.Platform
import reactiveComponent.framework.SimpleTransformation
import reactiveComponent.nbiot.dependency.L2Service
import reactiveComponent.nbiot.flow.CellLoadBalanceCheck.CellUECount
import reactiveComponent.nbiot.source.UL_CCCH_MSG

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class RRCConnectionSetup extends SimpleTransformation[(UL_CCCH_MSG, CellUECount), Boolean] {
  override def doUpdate(input: (UL_CCCH_MSG, CellUECount)): Future[Boolean] = {
    val rRCRejectL2Notify: RRCRejectL2Notify = new RRCRejectL2Notify(L2Service.rejectNotify)
    val rRCLocalRelease: RRCLocalRelease = new RRCLocalRelease()


    val (uL_CCCH_MSG, cellUECount) = input
    val ueId = uL_CCCH_MSG.ueId
    val cellId = uL_CCCH_MSG.cellId

    Platform.run(new CellLoadBalanceCheck())(input).flatMap { checkResult =>
      if (checkResult.allow) {
        Platform.run(new RRCInstanceSetup)(uL_CCCH_MSG).flatMap { rBSetupReq: RBSetup.RBSetupReq =>
          Platform.run(new RBSetup)(rBSetupReq, RBSetup.Model(ueId, cellId))
        }
      }
      else {
        Platform.run(new RRCConnectionReject)(UeIdentity(ueId, cellId))
      }
    }
  }

  override def processName: String = "RRCConnectionSetup"
}
