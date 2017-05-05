package concurrency.par

import org.scalatest.{FunSpec, Matchers}

class MockCustomerRepositorySpec extends FunSpec with Matchers{
  describe("MockCustomerRepository"){
    it("can get customer name by id"){
      val customerDataProvider = new MockCustomerRepository {
        override def customerData: Set[(CustomerId, Name)] = Set("1" -> "xx", "2" -> "yy", "3" -> "zz")
      }
      customerDataProvider.fetch("1").get shouldBe "xx"
      customerDataProvider.fetch("2").get shouldBe "yy"
      customerDataProvider.fetch("3").get shouldBe "zz"
      customerDataProvider.fetch("4") shouldBe None
    }
  }
}
