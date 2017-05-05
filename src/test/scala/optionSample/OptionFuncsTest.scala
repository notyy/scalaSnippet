package optionSample

import optionSample.OptionFuncs._
import org.scalatest.{FunSpec, Matchers}

class OptionFuncsTest extends FunSpec with Matchers {
  describe("map2"){
    it("should combines two options"){
      map2(Some(List(1)), Some(2))((a,b) => a :+ b) shouldBe Some(List(1,2))
      map2(Some(List(1)), None)((a,b) => a :+ b) shouldBe None
    }
  }

  describe("sequence"){
    it("should extract values from option list"){
      sequence(List(Some(1),None,Some(2))) shouldBe None
      sequence(List(Some(1),Some(2))) shouldBe Some(List(1,2))
    }
  }
}
