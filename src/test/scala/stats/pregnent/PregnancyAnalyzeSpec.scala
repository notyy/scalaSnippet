package stats.pregnent

import org.scalatest.{ShouldMatchers, FunSpec}
import PregnancyAnalyze._

class PregnancyAnalyzeSpec extends FunSpec with ShouldMatchers {
  describe("PregnancyAnalyze"){
    it("can extract Pregnancy instance from string presentation"){
      val s = "           1 1     6 1     11093 1084     9 039 9   0  1 813             1093 13837                        1                5                                                                        116610931166 9201093                111             3    1   12  11         5391 110933316108432411211     2995 1212 69544441116122222 2 2224693215    000000000000000000000000000000000000003410.38939935294273869.3496019830486 6448.2711117047512 91231"
      val p = extract(s)
      p.caseId shouldBe 1
      p.prgLength shouldBe 39
      p.outcome shouldBe 1
      p.birthOrd shouldBe 1
      p.finalWgt shouldBe 6448.271F
    }
  }
}
