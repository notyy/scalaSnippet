package implicits.db

import java.sql.{DriverManager, Connection}

trait ConnectionFactory {
  def createConnection: Connection
}

trait H2ConnectionFactory extends ConnectionFactory {
  //empty implementation,just for demo
  override def createConnection: Connection = {
    Class.forName("org.h2.Driver")
    DriverManager.getConnection("jdbc:h2:~/test", "sa", "")
  }
}
