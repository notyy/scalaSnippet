package loanPattern

import org.scalatest.{ShouldMatchers, FunSpec}

class SampleTxtProcessorTest extends FunSpec with ShouldMatchers {
  describe("SampleTxtProcessor"){
    it("can count how many lines are there in a file"){
      SampleTxtProcessor.countLines("src/test/resources/sample.txt") shouldBe 3
    }
  }
}
