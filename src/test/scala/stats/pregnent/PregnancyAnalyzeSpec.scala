package stats.pregnent

import org.scalatest.{FunSpec, Matchers}
import PregnancyAnalyze._

class PregnancyAnalyzeSpec extends FunSpec with Matchers {
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
    it("if birth order string is empty, result should be -1"){
      val s = "           1 1     6 1     11093 1084     9 039 9   0  1 813             1093 13837                        1                5                                                                        116610931166 9201093                111             3    1   12  11         5391  10933316108432411211     2995 1212 69544441116122222 2 2224693215    000000000000000000000000000000000000003410.38939935294273869.3496019830486 6448.2711117047512 91231"
      val p = extract(s)
      p.birthOrd shouldBe -1
    }
    it("can calculate times of live birth"){
      liveBirthCount(List(
        Pregnancy(1,1,outcome = 1,1,1),
        Pregnancy(1,1,outcome = 0,1,1)
      )) shouldBe 1
    }
    it("can split live birth records to first birth group and others"){
      val pregnancies = List(
        Pregnancy(1,1,outcome = 1,birthOrd = 1,1),
        Pregnancy(2,1,outcome = 1,birthOrd = 2,1),
        Pregnancy(3,1,outcome = 1,birthOrd = 1,1),
        Pregnancy(3,1,outcome = 1,birthOrd = 3,1),
        Pregnancy(3,1,outcome = 1,birthOrd = 4,1),
        Pregnancy(4,1,outcome = 0,1,1)
      )
      val (firstLives, others) = liveBirthGroupByBirthOrder(pregnancies)
      firstLives should have size 2
      others should have size 3
    }
    it("can calculate avg pregLength"){
      val pregnancies = List(
        Pregnancy(1,prgLength = 30,1,1,1),
        Pregnancy(2,prgLength = 40,1,2,1)
      )
      avgPrgLength(pregnancies) shouldBe 35f
    }
  }
}
