package slick.repo

import slick.domain.Customer
import slick.{DBConfigProvider, Database}

import scala.concurrent.Future

trait CustomerRepo {
  val database: Database

  import database.customers
  import database.databaseApi._

  def register(name: String): Future[Customer] = {
    val q = (customers returning customers.map(_.id)
      into ((customer, id) => customer.copy(id = id))
      ) += Customer(None, name, None)
    database.run(q)
  }

  def listAll: Future[Seq[Customer]] = database.run(customers.result)

  def findByNameLike(name: String): Future[Seq[Customer]] = {
    val q = customers.filter(_.name like "%notyy%")
    database.run(q.result)
  }
}