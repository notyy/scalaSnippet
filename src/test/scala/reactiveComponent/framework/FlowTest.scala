package reactiveComponent.framework

import org.scalatest.{FunSpec, ShouldMatchers}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.language.postfixOps

class FlowTest extends FunSpec with ShouldMatchers {
  describe("Flow") {
    it("can combine small component and run") {
      val step1 = new SimpleTransformation[Int, Int] {
        override def update(input: Int): Future[Int] = Future {
          input + 1
        }
      }

      val step2 = new SimpleTransformation[Int, String] {
        override def update(input: Int): Future[String] = Future {
          input.toString
        }
      }

      Await.result(CombinedSteps(step1, LastStep(step2)).runThrough(1), 1 seconds) shouldBe "2"
    }
  }
}
