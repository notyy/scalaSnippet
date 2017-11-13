package slick.repo

import com.typesafe.scalalogging.StrictLogging
import slick.Database

import scala.concurrent.Future

trait OrderRepo extends StrictLogging {
  val database: Database

  import database._
  import database.databaseApi._

  def createOrder(customerId: String, productId: String): Future[String] = {
    database.run((orders returning orders.map(_.id)
      into ((_,id) => id.get)
      ) += (None, customerId, productId))
  }
}
