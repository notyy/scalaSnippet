package slick.repo

import org.scalatest.{BeforeAndAfter, FunSpec, Matchers}
import slick.Database

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps

class CustomerRepoTest extends FunSpec with Matchers with BeforeAndAfter {
  val database = Database

  before {
    Await.result(database.setupDB(), 5 seconds)
  }

  after {
    Await.result(database.dropDB(), 5 seconds)
  }

  describe("CustomerRepo"){
    it("allow user to register customer into database"){
      Await.result(CustomerRepo.listAll, 5 seconds).length shouldBe 0
      val customer = Await.result(CustomerRepo.register("notyy"), 5 seconds)
      customer.id should not be empty
      Await.result(CustomerRepo.listAll, 5 seconds).length shouldBe 1
    }

    it("can query user by name fuzzily"){
      Await.result(CustomerRepo.listAll, 5 seconds).length shouldBe 0
      Await.result(CustomerRepo.register("notyyXXXzz"), 5 seconds)
      Await.result(CustomerRepo.register("notyyYYYzz"), 5 seconds)
      Await.result(CustomerRepo.register("xxxxYYYzz"), 5 seconds)
      Await.result(CustomerRepo.listAll, 5 seconds) should have size 3

      val customers = Await.result(CustomerRepo.findByNameLike("notyy"), 5 seconds)
      customers should have size 2
      customers.forall(_.name.contains("notyy"))
    }
  }
}
