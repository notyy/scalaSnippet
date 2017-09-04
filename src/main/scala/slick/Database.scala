package slick

import slick.domain.Customer

import scala.concurrent.Future

trait Database {
  this: DBConfigProvider =>

  lazy val databaseApi: jdbcProfile.API = jdbcProfile.api

  import databaseApi._

  def run[T](action: slick.dbio.DBIOAction[T, NoStream, Nothing]): Future[T] = {
    db.run(action)
  }


  class CustomerTable(tag: Tag) extends Table[Customer](tag, "customer") {
    def id = column[Option[String]]("ID", O.PrimaryKey, O.AutoInc)

    def name = column[String]("NAME")

    def birthday = column[Option[String]]("BIRTHDAY")

    def * = (id, name, birthday) <> (Customer.tupled, Customer.unapply)
  }

  val customers = TableQuery[CustomerTable]

  def setupDB(): Future[Unit] = {
    println("create tables:")
    customers.schema.createStatements.foreach(println)
    val setUp = DBIO.seq(
      customers.schema.create
    )
    run(setUp)
  }

  def dropDB(): Future[Unit] = {
    println("delete tables:")
    val drop = DBIO.seq(
      customers.schema.drop
    )
    run(drop)
  }
}
