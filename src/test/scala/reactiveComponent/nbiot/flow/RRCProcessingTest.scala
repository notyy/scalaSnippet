package reactiveComponent.nbiot.flow

import org.scalatest.{FunSpec, ShouldMatchers}
import reactiveComponent.Platform
import reactiveComponent.nbiot.flow.RRCProcessing.RRCConnReq
import reactiveComponent.nbiot.source.UL_CCCH_MSG

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps

class RRCProcessingTest extends FunSpec with ShouldMatchers {
  describe("RRCProcessing") {
    it("can process rrc connection req") {
      Await.result(
        Platform.run(new RRCProcessing)(RRCConnReq(UL_CCCH_MSG("some content", CellId("C1001"), UeId("U1001"))), None),
        3 seconds
      )
    }
  }
}
