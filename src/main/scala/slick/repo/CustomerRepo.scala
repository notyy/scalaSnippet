package slick.repo

import com.typesafe.scalalogging.StrictLogging
import slick.Database
import slick.domain._

import scala.concurrent.Future

trait CustomerRepo extends StrictLogging {
  val database: Database

  import database.customers
  import database.databaseApi._

  def register(name: String,userType: UserType): Future[Customer] = {
    val q = (customers returning customers.map(_.id)
      into ((customer, id) => customer.copy(id = id))
      ) += Customer(None, name, None,userType,AuditInfo())

    q.statements.foreach(s => logger.info(s))
    database.run(q)
  }

  def nextId(seqName: String): Future[Int] = {
    val rs = database.run[Int](Sequence[Int](seqName).next.result)
    rs
  }

  def listAll: Future[Seq[Customer]] = database.run(customers.result)

  def findByNameLike(name: String): Future[Seq[Customer]] = {
    val q = customers.filter(_.name like "%notyy%")
    database.run(q.result)
  }

  def findByUserType(userType: UserType): Future[Seq[Customer]] = {
    val q = customers.filter(_.userType === userType)
    database.run(q.result)
  }
}