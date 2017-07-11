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
    it("can merge map by implicit"){
      val map1 = Map("a" -> 1,"b" -> 1)
      val map2 = Map("b" -> 1, "c" -> 1)
      import MapUtil._
      val result = map1.merge(map2)(_ + _)
      result should have size 3
      result("a") shouldBe 1
      result("b") shouldBe 2
      result("c") shouldBe 1
    }
    it("can merge more than 2 maps"){
      val map1 = Map("a" -> 1,"b" -> 1)
      val map2 = Map("b" -> 1, "c" -> 1)
      val map3 = Map("c" -> 1, "d" -> 1)
      val result = MapUtil.mergeMaps(map1, map2, map3)(_ + _)
      result should have size 4
      result("a") shouldBe 1
      result("b") shouldBe 2
      result("c") shouldBe 2
      result("d") shouldBe 1
    }
  }
}
