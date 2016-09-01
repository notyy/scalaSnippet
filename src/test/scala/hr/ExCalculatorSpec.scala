package hr

import org.scalatest.{FunSpec, ShouldMatchers}

class ExCalculatorSpec extends FunSpec with ShouldMatchers {
  describe("Excalculator"){
    it("can calculator e^x"){
//      ExCalculator.f(20.0000f) shouldBe 2423600.1887
      ExCalculator.f(5.0000f) shouldBe 143.6895
      ExCalculator.f(0.5000f) shouldBe 1.6487
      ExCalculator.f(-0.5000f) shouldBe 0.6065
    }
    it("can calculator e^x given any terms"){
      ExCalculator.ff(1,1) shouldBe 2
      ExCalculator.ff(1,2) shouldBe 2.5
    }
  }
}
