package reactiveComponent.framework

import org.scalatest.{FunSpec, Matchers}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.language.postfixOps

class FlowTest extends FunSpec with Matchers {
  describe("Flow") {
    it("can combine small component and run") {
      val step1 = new SimpleTransformation[Int, Int] {
        override def doUpdate(input: Int): Future[Int] = Future {
          input + 1
        }

        override def processName: String = "Step1"
      }

      val step2 = new SimpleTransformation[Int, String] {
        override def doUpdate(input: Int): Future[String] = Future {
          input.toString
        }

        override def processName: String = "Step2"
      }

      Await.result(CombinedSteps(step1, LastStep(step2)).runThrough(1), 1 seconds) shouldBe "2"
    }
  }
}
