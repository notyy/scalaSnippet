package implicits.db

import java.sql.ResultSet

trait DBAccessor {
  this: DataSource with SQLExecutor =>

  def query(sql: SQL): List[List[String]] = {
    withConnection { conn =>
      executeQuery(sql)(rsToStringTable)(conn)
    }
  }

  def rsToStringTable(rs: ResultSet): List[List[String]] = ???
}

trait H2DBAccessorWithTransactionSupport extends DBAccessor with DataSource with H2ConnectionFactory with DefaultSQLExecutor with TransactionManager
