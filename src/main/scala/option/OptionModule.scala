package option

/**
 * this exercise show usage of Option instead of null
 * 1. let use implement ??? methods to return null in some condition
 * 2. show how complex it could be to do those null checking when implement fetch email
 * 3. don't forget to write unit tests!
 * 4. use Option instead of null
 * 5. show possible usage of Option in fetch email function
 * 5.1 use pattern matching
 * 5.2 use flatMap
 * 5.3 use for expression
 */
object OptionModule {

  case class User(name: String, age: Int)

  case class Connection(conn: String) extends AnyVal

  case class Email(value: String) extends AnyVal

  def getConnection(connStr: String): Connection = ???

  def getUserInfo(conn: Connection, name: String): User = ???

  def getEmail(user: User): Email = ???

  def fetchEmail(connStr: String, name: String): Email = ???
}
