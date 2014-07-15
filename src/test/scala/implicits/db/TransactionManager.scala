package implicits.db

import java.sql.Connection

trait TransactionManager {
  this: DataSource =>

  def inTransaction[T](f: Connection => T): T = {
    withConnection{ conn =>
      conn.setAutoCommit(false)
      val rs = f(conn)
      conn.commit()
      rs
    }
  }
}
