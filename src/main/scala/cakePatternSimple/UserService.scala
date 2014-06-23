package cakePatternSimple

trait UserService {
  def findAll: List[User]
}

trait UserRepository {
  def loadUser: List[User]
}

trait TransactionManager {
  def executeSQL(sql: String): List[User]
}

trait TransactionManagerImpl extends TransactionManager{
  override def executeSQL(sql: String): List[User] = ???
}

trait UserRepositoryImpl extends UserRepository{
  this: TransactionManager =>
  override def loadUser: List[User] = executeSQL("SELECT * FROM user")
}

trait DefaultUserRepositoryImpl extends UserRepositoryImpl with TransactionManagerImpl
trait UserServiceImpl extends UserService {
  this: UserRepository =>
  override def findAll: List[User] = loadUser
}

