package reactiveComponent.framework

import org.scalatest.{FunSpec, ShouldMatchers}

class FlowTest extends FunSpec with ShouldMatchers{
  describe("Flow"){
    it("can combine small component and run"){
      val step1 = new SimpleTransformation[Int, Int] {
        override def update(input: Int): Int = input + 1
      }

      val step2 = new SimpleTransformation[Int, String] {
        override def update(input: Int): String = input.toString
      }

      CombinedSteps(step1, LastStep(step2)).runThrough(1) shouldBe "2"
    }
  }
}
