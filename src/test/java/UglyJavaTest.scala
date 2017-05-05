import java.util

import org.scalatest.{FunSpec, Matchers}

class UglyJavaTest extends FunSpec with Matchers {
  describe("UglyJava"){
    ignore("sum a given List"){
      val uj = new UglyJava
      val list = new util.ArrayList[Integer](util.Arrays.asList[Integer](1, 2, 3, 4, 5))
      uj.getSum(list) shouldBe 24
//      list shouldBe util.Arrays.asList[Integer](1, 2, 3, 4, 5)
    }
  }
}
