package reactiveComponent.nbiot.flow

import reactiveComponent.Platform
import reactiveComponent.framework.SimpleTransformation
import reactiveComponent.nbiot.dependency.L2Service
import reactiveComponent.nbiot.flow.CellLoadBalanceCheck.CellUECount
import reactiveComponent.nbiot.source.UL_CCCH_MSG

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class RRCConnectionSetup extends SimpleTransformation[(UL_CCCH_MSG, CellUECount), Boolean] {
  override def update(input: (UL_CCCH_MSG, CellUECount)): Future[Boolean] = {
    val cellLoadBalanceCheck: CellLoadBalanceCheck = new CellLoadBalanceCheck()
    val rRCRejectL2Notify: RRCRejectL2Notify = new RRCRejectL2Notify(L2Service.rejectNotify)
    val rRCLocalRelease: RRCLocalRelease = new RRCLocalRelease()


    val (uL_CCCH_MSG, cellUECount) = input

    Platform.run(cellLoadBalanceCheck)(input).map { checkResult =>
      if(checkResult.allow) {
        true
      }
      else {
        false
      }
    }
  }
}
