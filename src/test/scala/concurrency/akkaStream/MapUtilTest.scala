package concurrency.akkaStream

import org.scalatest.{FunSpec, Matchers}

class MapUtilTest extends FunSpec with Matchers {
  describe("MapUtil"){
    it("can merge two maps"){
      val map1 = Map("a" -> 1,"b" -> 1)
      val map2 = Map("b" -> 1, "c" -> 1)
      val result = MapUtil.mergeMaps(map1, map2)(_ + _)
      result should have size 3
      result("a") shouldBe 1
      result("b") shouldBe 2
      result("c") shouldBe 1
    }
  }
}
