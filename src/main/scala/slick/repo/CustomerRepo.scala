package slick.repo

import slick.Database
import slick.domain.Customer

import scala.concurrent.Future

class CustomerRepo {
  this: Database =>

  import databaseApi._

  def register(name: String): Future[Customer] = {
    val q = (customers returning customers.map(_.id)
      into ((customer, id) => customer.copy(id = id))
      ) += Customer(None, name, None)
    run(q)
  }

  def listAll: Future[Seq[Customer]] = run(customers.result)
}
