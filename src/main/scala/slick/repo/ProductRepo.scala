package slick.repo

import com.typesafe.scalalogging.StrictLogging
import slick.Database
import slick.domain.Product

import scala.concurrent.Future

trait ProductRepo extends StrictLogging {
  val database: Database

  import database._
  import database.databaseApi._

  def addProduct(name: String): Future[Product] = {
    database.run((products returning products.map(_.id)
      into ((product, id) => product.copy(id = id))
      ) += Product(None, name))
  }

  def listProducts(): Future[Seq[Product]] = {
    database.run(products.result)
  }
}
