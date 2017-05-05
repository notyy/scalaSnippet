package concurrency.par

import org.scalatest.{FunSpec, Matchers}

class OrderDataSelectorSpec extends FunSpec with Matchers{
  describe("MasterDataProvider"){
    it("can filter data by order amount"){
      val orderDataProvider = new MockOrderRepository {
        override val orderData: Set[(CustomerId, OrderAmount)] = Set("1" -> 30, "1" -> 50, "2" -> 10, "3" -> 100)
      }
      val customerIds = orderDataProvider.filterByAmount(_ > 10).map(_._1)
      customerIds.size shouldBe 2
      customerIds should contain ("1")
      customerIds should contain ("3")
      customerIds shouldNot contain ("2")
    }
  }
}
