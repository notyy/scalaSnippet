package slick.service

import com.typesafe.scalalogging.StrictLogging
import org.scalatest.{BeforeAndAfter, FunSpec, Matchers}
import slick.domain.CommonUser
import slick.{DBConfigProvider, Database}
import slick.repo.{CustomerRepo, OrderRepo, ProductRepo, TestDatabase}

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps

class OrderServiceSpec extends FunSpec with Matchers with BeforeAndAfter with StrictLogging with TestDatabase {

  val customerRepo = new CustomerRepo with DBConfigProvider {
    override val database: Database = myDatabase
  }

  val productRepo = new ProductRepo with DBConfigProvider {
    override val database: Database = myDatabase
  }

  before{
    Await.result(myDatabase.setupDB(), 5 seconds)
  }

  after {
    Await.result(myDatabase.dropDB(), 5 seconds)
  }

  describe("OrderService"){
    it("can create order"){
      val orderService:OrderService = new OrderService with OrderRepo with DBConfigProvider {
        override val database = myDatabase
      }
      val customer = Await.result(customerRepo.register("notyy",CommonUser), 5 seconds)
      val product = Await.result(productRepo.addProduct("iphoneX"), 5 seconds)
      val order =  Await.result(orderService.createOrder(customer,product), 5 seconds)
      order.id shouldBe defined
      order.customer shouldBe customer
      order.product shouldBe product
    }
  }
}
