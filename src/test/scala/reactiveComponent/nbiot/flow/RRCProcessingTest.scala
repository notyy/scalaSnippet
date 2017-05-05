package reactiveComponent.nbiot.flow

import com.typesafe.scalalogging.slf4j.StrictLogging
import org.scalatest.{FunSpec, Matchers}
import reactiveComponent.Platform
import reactiveComponent.nbiot.flow.RRCProcessing.{NasTransport, RRCConnReq}
import reactiveComponent.nbiot.source.UL_CCCH_MSG

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps

class RRCProcessingTest extends FunSpec with Matchers with StrictLogging {
  describe("RRCProcessing") {
    it("can process rrc connection req") {
      val ueId: UeId = UeId("U1001")
      val cellId: CellId = CellId("C1001")

      val rrcInstance = Await.result(
        Platform.run(new RRCProcessing)(RRCConnReq(UL_CCCH_MSG("some content", cellId, ueId)), None),
        3 seconds
      )
      logger.info(s"rrcInstance is: $rrcInstance")
      logger.info(s"platform cache has data: ${Platform.cache}")
      rrcInstance.get.ueId shouldBe ueId
      rrcInstance.get.cellId shouldBe cellId

      val nasTransportResult = Await.result(
        Platform.run(new RRCProcessing)(NasTransport(ueId,cellId,"some content"), None),
        3 seconds
      )
    }
  }
}
