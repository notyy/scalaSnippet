package option

object OptionModule {

  case class User(name: String, age: Int)

  case class Connection(conn: String) extends AnyVal

  case class Email(value: String) extends AnyVal

  def getConnection(connStr: String): Option[Connection] = {
    if (connStr == "yy") Some(Connection("yy")) else None
  }

  def getUserInfo(conn: Connection): Option[User] = ???

  def getEmail(user: User): Option[Email] = ???

  def fetchEmail(connStr: String): Option[Email] = ???
}
