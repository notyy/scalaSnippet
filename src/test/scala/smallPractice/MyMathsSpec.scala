package smallPractice

import org.scalatest.{FunSpec, Matchers}

class MyMathsSpec extends FunSpec with Matchers{
  describe("MyMaths"){
    it("can calculate max diviser of two ints"){
      MyMaths.maxDiviser(98, 63) shouldBe  7
    }
  }
}
