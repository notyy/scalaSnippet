package state

import org.scalatest.{ShouldMatchers, FunSpec}

class RNGTest extends FunSpec with ShouldMatchers {
  describe("RNG"){
    it("should generate list of ints"){
      val ints: (List[Int], RNG) = RNG.ints(4)(RNG.simple(1000))
      ints._1.size shouldBe 4
      ints._1.foreach(println)
    }

    it("can generate int up to max parameter") {
      val (i,_) = RNG.positiveMax(5)(RNG.simple(600))
      i should be <= 5
    }
  }
}
