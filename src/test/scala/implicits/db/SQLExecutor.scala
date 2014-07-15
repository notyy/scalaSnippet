package implicits.db

import java.sql.{Connection, ResultSet}

trait SQLExecutor {
  def executeQuery[T](sql: SQL)(f: ResultSet => T)(implicit conn: Connection): T

  def executeUpdate(sql: SQL)(implicit conn: Connection): Boolean
}

trait DefaultSQLExecutor extends SQLExecutor {
  override def executeQuery[T](sql: SQL)(f: (ResultSet) => T)(implicit conn: Connection): T = ???

  override def executeUpdate(sql: SQL)(implicit conn: Connection): Boolean = ???
}
