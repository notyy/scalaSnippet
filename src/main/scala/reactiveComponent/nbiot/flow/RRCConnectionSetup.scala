package reactiveComponent.nbiot.flow

import reactiveComponent.Platform
import reactiveComponent.framework.SimpleTransformation
import reactiveComponent.nbiot.dependency.L2Service
import reactiveComponent.nbiot.flow.CellLoadBalanceCheck.CellUECount
import reactiveComponent.nbiot.flow.RRCProcessing.RRCInstance
import reactiveComponent.nbiot.source.UL_CCCH_MSG

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

class RRCConnectionSetup extends SimpleTransformation[(UL_CCCH_MSG, CellUECount), Try[RRCInstance]] {
  override def doUpdate(input: (UL_CCCH_MSG, CellUECount)): Future[Try[RRCInstance]] = {
    val rRCRejectL2Notify: RRCRejectL2Notify = new RRCRejectL2Notify(L2Service.rejectNotify)
    val rRCLocalRelease: RRCLocalRelease = new RRCLocalRelease()


    val (uL_CCCH_MSG, cellUECount) = input
    val ueId = uL_CCCH_MSG.ueId
    val cellId = uL_CCCH_MSG.cellId

    val fRRCInstance = Platform.run(new CellLoadBalanceCheck())(input).flatMap { checkResult =>
      if (checkResult.allow) {
        Platform.run(new RRCInstanceSetup)(uL_CCCH_MSG).map {
          rRCInstance => Success(rRCInstance)
        }
      } else {
        Platform.run(new RRCConnectionReject)(UeIdentity(ueId, cellId)).
          map(_ => Failure(new IllegalStateException("exceed cell load capacity")))
      }
    }

    //      .flatMap { rBSetupReq: RBSetup.RBSetupReq =>
    //        Platform.run(new RBSetup)(rBSetupReq).map {
    //          case true => Success(RRCInstance(ueId, "some reason", cellId, 0.50))
    //          case false => Failure(new IllegalStateException("RBSetup return's false"))
    //        }
    //      }
    fRRCInstance
  }

  override def processName: String = "RRCConnectionSetup"
}
