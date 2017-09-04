package slick.repo

import org.scalactic.source.Position
import org.scalatest.{BeforeAndAfter, FunSpec, Matchers}
import slick.{Database, H2DB}

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps

class CustomerRepoTest extends FunSpec with Matchers with BeforeAndAfter {
  val database = new Database with H2DB

  before {
    Await.result(database.setupDB(), 5 seconds)
  }

  after {
    Await.result(database.dropDB(), 5 seconds)
  }

  describe("CustomerRepo"){
    it("allow user to register customer into database"){
      val customerRepo = new CustomerRepo with Database with H2DB
      Await.result(customerRepo.listAll, 5 seconds).length shouldBe 0
      val customer = Await.result(customerRepo.register("notyy"), 5 seconds)
      customer.id should not be empty
      Await.result(customerRepo.listAll, 5 seconds).length shouldBe 1
    }
  }
}
