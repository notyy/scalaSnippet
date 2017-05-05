package loanPattern

import org.scalatest.{FunSpec, Matchers}

class SampleTxtProcessorLoanImplTest extends FunSpec with Matchers {
  describe("SampleTxtProcessorLoanImpl"){
    it("can count how many lines are there in a file"){
      val txtProcessor = new SampleTxtProcessorLoanImpl with FileReadSupport
      txtProcessor.countLines("src/test/resources/sample.txt") shouldBe 3
    }
  }
}
