package concurrency.par

import org.scalatest.{ShouldMatchers, FunSpec}

class CustomerDataProviderSpec extends FunSpec with ShouldMatchers{
  describe("CustomerDataProvider"){
    it("can get customer name by id"){
      val customerDataProvider = new CustomerDataProvider {
        override def customerData: Set[(CustomerId, Name)] = Set("1" -> "xx", "2" -> "yy", "3" -> "zz")
      }
      customerDataProvider.fetch("1").get shouldBe "xx"
      customerDataProvider.fetch("2").get shouldBe "yy"
      customerDataProvider.fetch("3").get shouldBe "zz"
      customerDataProvider.fetch("4") shouldBe None
    }
  }
}
