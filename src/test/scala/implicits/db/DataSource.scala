package implicits.db

import java.sql.Connection

trait DataSource {
  this: ConnectionFactory =>

  def withConnection[T](f: Connection => T): T = {
    val conn = createConnection
    try {
      f(conn)
    }finally {
      conn.close()
    }
  }
}
