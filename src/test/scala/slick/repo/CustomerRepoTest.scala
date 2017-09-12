package slick.repo

import org.scalatest.{BeforeAndAfter, FunSpec, Matchers}
import slick.{DBConfigProvider, Database}

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps

class CustomerRepoTest extends FunSpec with Matchers with BeforeAndAfter {
  val myDatabase = Database

  val customerRepo = new CustomerRepo with DBConfigProvider {
    override val database: Database = myDatabase
  }

  before {
    Await.result(myDatabase.setupDB(), 5 seconds)
  }

  after {
    Await.result(myDatabase.dropDB(), 5 seconds)
  }

  describe("CustomerRepo"){
    it("allow user to register customer into database"){
      Await.result(customerRepo.listAll, 5 seconds).length shouldBe 0
      val customer = Await.result(customerRepo.register("notyy"), 5 seconds)
      customer.id should not be empty
      Await.result(customerRepo.listAll, 5 seconds).length shouldBe 1
    }

    it("can query user by name fuzzily"){
      Await.result(customerRepo.listAll, 5 seconds).length shouldBe 0
      Await.result(customerRepo.register("notyyXXXzz"), 5 seconds)
      Await.result(customerRepo.register("notyyYYYzz"), 5 seconds)
      Await.result(customerRepo.register("xxxxYYYzz"), 5 seconds)
      Await.result(customerRepo.listAll, 5 seconds) should have size 3

      val customers = Await.result(customerRepo.findByNameLike("notyy"), 5 seconds)
      customers should have size 2
      customers.forall(_.name.contains("notyy"))
    }
  }
}
