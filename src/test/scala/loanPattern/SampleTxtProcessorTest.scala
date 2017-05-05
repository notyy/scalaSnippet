package loanPattern

import org.scalatest.{FunSpec, Matchers}

class SampleTxtProcessorTest extends FunSpec with Matchers {
  describe("SampleTxtProcessor"){
    it("can count how many lines are there in a file"){
      SampleTxtProcessor.countLines("src/test/resources/sample.txt") shouldBe 3
    }
  }
}
