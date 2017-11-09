package slick.repo

import java.sql.SQLException

import com.typesafe.scalalogging.StrictLogging
import org.scalatest.{BeforeAndAfter, FunSpec, Matchers}
import slick.domain.{CommonUser, SuperUser}
import slick.{DBConfigProvider, Database}

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps
import scala.util.{Failure, Success}

class CustomerRepoTest extends FunSpec with Matchers with BeforeAndAfter with StrictLogging with TestDatabase {
  val customerRepo = new CustomerRepo with DBConfigProvider {
    override val database: Database = myDatabase
  }

  before {
    Await.result(myDatabase.setupDB(), 5 seconds)
  }

  after {
    Await.result(myDatabase.dropDB(), 5 seconds)
  }

  describe("CustomerRepo") {
    it("allow user to register customer into database") {
      Await.result(customerRepo.listAll, 5 seconds).length shouldBe 0
      val customer = Await.result(customerRepo.register("notyy", SuperUser), 5 seconds)
      customer.id should not be empty
      Await.result(customerRepo.listAll, 5 seconds).length shouldBe 1
    }

    it("can query user by name fuzzily") {
      Await.result(customerRepo.listAll, 5 seconds).length shouldBe 0
      Await.result(customerRepo.register("notyyXXXzz", SuperUser), 5 seconds)
      Await.result(customerRepo.register("notyyYYYzz", SuperUser), 5 seconds)
      Await.result(customerRepo.register("xxxxYYYzz", CommonUser), 5 seconds)
      Await.result(customerRepo.listAll, 5 seconds) should have size 3
      Await.result(customerRepo.listAll, 5 seconds).foreach(println)

      println("find by name like notyy")
      val customers = Await.result(customerRepo.findByNameLike("notyy"), 5 seconds)
      println("find by name like notyy complete")
      customers should have size 2
      customers.foreach(println)
      customers.forall(_.name.contains("notyy")) shouldBe true
      customers.forall(_.auditInfo.created.isDefined) shouldBe true
    }
    it("can query by user type") {
      Await.result(customerRepo.listAll, 5 seconds).length shouldBe 0
      Await.result(customerRepo.register("notyyXXXzz", SuperUser), 5 seconds)
      Await.result(customerRepo.register("notyyYYYzz", SuperUser), 5 seconds)
      Await.result(customerRepo.register("xxxxYYYzz", CommonUser), 5 seconds)
      Await.result(customerRepo.listAll, 5 seconds) should have size 3

      val customers = Await.result(customerRepo.findByUserType(SuperUser), 5 seconds)
      customers should have size 2
      customers.foreach(println)
      customers.forall(_.name.contains("notyy")) shouldBe true
      customers.forall(_.auditInfo.created.isDefined) shouldBe true
    }
    it("supports transaction") {
      import myDatabase.databaseApi._

      val actions = DBIO.seq(
        customerRepo.registerAction("notyy", CommonUser),
        customerRepo.registerAction("notyy", SuperUser) //name is unique, so it should fail
      ).transactionally
      import scala.concurrent.ExecutionContext.Implicits.global
      val rs = myDatabase.run(actions)
      rs.onComplete {
        case Success(_) => fail("should not success")
        case Failure(ex) => logger.error("error occur with sql {}", ex)
      }
      Await.ready(rs, 5 seconds)
      Await.result(customerRepo.listAll, 5 seconds).length shouldBe 0
    }
  }
}
