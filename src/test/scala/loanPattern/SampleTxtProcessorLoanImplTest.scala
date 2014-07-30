package loanPattern

import org.scalatest.{ShouldMatchers, FunSpec}

class SampleTxtProcessorLoanImplTest extends FunSpec with ShouldMatchers {
  describe("SampleTxtProcessorLoanImpl"){
    it("can count how many lines are there in a file"){
      val txtProcessor = new SampleTxtProcessorLoanImpl with FileReadSupport
      txtProcessor.countLines("src/test/resources/sample.txt") shouldBe 3
    }
  }
}
