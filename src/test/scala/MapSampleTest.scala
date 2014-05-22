import scala.collection.JavaConversions._
import org.scalatest.{ShouldMatchers, FunSpec}

class MapSampleTest extends FunSpec with ShouldMatchers {
  describe("a MapSample"){
    it("should select element that is >2 , then do * 2 to each element"){
      val mapSample = new MapSample()
      val list = List[Integer](1,2,3,4,5)
      val rs = mapSample.process(list)
      rs.size() should be (3)
      rs.toList should be (List(6,8,10))
    }
  }

}
