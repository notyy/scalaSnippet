import org.scalatest.{ShouldMatchers, FunSpec}
import scala.collection.JavaConversions._

class ListUtilTest extends FunSpec with ShouldMatchers {
  describe("ListUtil") {
    it("allows to apply filter function to a List") {
      val list = List(1, 2, 3, 4, 5)
      val result = ListUtil.filter(list, new FilterFunction[Int] {
        def filter(item: Int): Boolean = item > 2
      })
      result.size() should be(3)
      result.toList should be(List(3, 4, 5))
    }

    it("allows to apply map function to a list") {
      val list = List(1, 2, 3, 4, 5)
      val result = ListUtil.map(list, new MapFunction[Int, Int] {
        def map(item: Int): Int = item * 2
      })
      result.size() should be (5)
      result.toList should be(List(2,4,6,8,10))
    }
    it("generate result as list, so it can be combined"){
      val list = List[Integer](1,2,3,4,5)
      val rs1 = ListUtil.filter(list, new FilterFunction[Integer] {
        def filter(item: Integer): Boolean = item > 2
      })
      val rs2 = ListUtil.map(rs1, new MapFunction[Integer,Integer] {
        def map(item: Integer): Integer = item * 2
      })
      rs2.size() should be (3)
      rs2.toList should be (List(6,8,10))
    }
  }
}
