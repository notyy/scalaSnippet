package reactiveComponent.nbiot.flow

import com.typesafe.scalalogging.StrictLogging
import org.scalatest.{FunSpec, Matchers}
import reactiveComponent.Platform
import reactiveComponent.nbiot.flow.CellLoadBalanceCheck.CellUECount
import reactiveComponent.nbiot.source.UL_CCCH_MSG

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps

class CellLoadBalanceCheckTest extends FunSpec with Matchers with StrictLogging {
  describe("CellLoadBalanceCheck") {
    it("should check cell load balance") {
      val checkResult = (new CellLoadBalanceCheck).update(UL_CCCH_MSG("content", CellId("1"), UeId("5")), CellUECount(4))
      val future = Await.ready(checkResult, 3 seconds)
      logger.info(s"checkResult: $future")
    }
    it("can also be run from Platform") {
      val checkResult = Platform.run(new CellLoadBalanceCheck)(UL_CCCH_MSG("content", CellId("1"), UeId("5")), CellUECount(4))
      val future = Await.ready(checkResult, 3 seconds)
      logger.info(s"checkResult: $future")
    }
  }
}
