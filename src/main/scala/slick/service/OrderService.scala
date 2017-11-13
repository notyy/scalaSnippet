package slick.service

import slick.domain.{Customer, Order, Product}
import slick.repo.OrderRepo

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait OrderService {
  this: OrderRepo =>

  def createOrder(customer: Customer, product: Product): Future[Order] = {
    createOrder(customer.id.get, product.id.get).map(
      oid => Order(Some(oid), customer,product)
    )
  }
}
