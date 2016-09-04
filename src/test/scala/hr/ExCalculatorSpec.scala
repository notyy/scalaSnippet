package hr

import org.scalatest.{FunSpec, ShouldMatchers}
import utils.FloatPointUtil

class ExCalculatorSpec extends FunSpec with ShouldMatchers {
  describe("Excalculator"){
    it("can calculator e^x"){
      ExCalculator.f(20.0000f) shouldBe (2423600.1887f +- 0.0001f)
      ExCalculator.f(5.0000f) shouldBe (143.6895f +- 0.0001f)
      ExCalculator.f(0.5000f) shouldBe (1.6487f +- 0.0001f)
      ExCalculator.f(-0.5000f) shouldBe (0.6065f +- 0.0001f)
    }
    it("can calculator e^x given any terms"){
      ExCalculator.ff(1,1) shouldBe 2
      ExCalculator.ff(1,2) shouldBe 2.5
    }
  }
}
