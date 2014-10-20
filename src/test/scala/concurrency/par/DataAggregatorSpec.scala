package concurrency.par

import org.scalatest.{ShouldMatchers, FunSpec}

class DataAggregatorSpec extends FunSpec with ShouldMatchers {
  describe("DataAggregatorSpec") {
    it("can bind order amount and customer name") {
      object dataAggregator extends DataAggregator with CustomerDataProvider with OrderDataSelector {
        override val customerData: Set[(CustomerId, Name)] = Set("1" -> "xx", "2" -> "yy", "3" -> "zz")

        override val orderData: Set[(CustomerId, OrderAmount)] = Set("1" -> 30, "1" -> 50, "2" -> 10, "3" -> 100)
      }

      val rs = dataAggregator.aggregate(_ > 10)
      rs should have size 3
      rs should contain("xx" -> 30)
      rs should contain("xx" -> 50)
      rs should contain("zz" -> 100)
      rs should not contain ("yy" -> 10)
    }
  }
}
