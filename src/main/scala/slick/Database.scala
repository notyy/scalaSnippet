package slick

import java.sql.Timestamp
import java.util.Date

import slick.domain.{AuditInfo, Customer}

import scala.concurrent.Future

trait Database {
  this: DBConfigProvider =>

  val dbConfig = getDatabaseConfig("h2mem1")

  lazy val databaseApi = dbConfig.profile.api

  import dbConfig.profile.api._

  def run[T](action: slick.dbio.DBIOAction[T, NoStream, Nothing]): Future[T] = {
    dbConfig.db.run(action)
  }

  implicit def mapDate = MappedColumnType.base[Date, java.sql.Timestamp](
    d => if(d==null) null else new Timestamp(d.getTime),
    ts => new Date(ts.getTime)
  )

  abstract class AuditTable[T](tag: Tag, tableName: String) extends Table[T](tag: Tag, tableName:String){
    def created = column[Option[Date]]("CREATED",O.SqlType("TIMESTAMP AS CURRENT_TIMESTAMP NOT NULL"))
    def createdBy = column[Option[String]]("CREATED_BY")
    def updated = column[Option[Date]]("UPDATED",O.SqlType("TIMESTAMP AS CURRENT_TIMESTAMP NOT NULL"))
    def updatedBy = column[Option[String]]("UPDATED_BY")
  }


  class CustomerTable(tag: Tag) extends AuditTable[Customer](tag,"customer") {
    def id = column[Option[String]]("ID", O.PrimaryKey, O.AutoInc)

    def name = column[String]("NAME")

    def birthday = column[Option[String]]("BIRTHDAY")

    def * = (id, name, birthday, (created, createdBy,updated,updatedBy)) <>
      (
        {
          case (id,name,birthday, audit ) =>
            Customer(id,name,birthday,AuditInfo.tupled.apply(audit))
        },
        {
          c: Customer => Some(c.id,c.name,c.birthday,AuditInfo.unapply(c.auditInfo).get)
        }
      )
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

object Database extends Database with DBConfigProvider
