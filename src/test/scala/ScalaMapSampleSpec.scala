import org.scalatest.{FunSpec, Matchers}

class ScalaMapSampleSpec extends FunSpec with Matchers {
  describe("a MapSample") {
    it("should select element that is >2 , then do * 2 to each element") {
      val list = List(1, 2, 3, 4, 5)
      val rs = ScalaMapSample.process(list)
      rs should have size 3
      rs.toList shouldBe List(6, 8, 10)
    }
  }
}
