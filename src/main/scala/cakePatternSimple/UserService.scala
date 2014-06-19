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

trait DefaultTransactionManager extends TransactionManager{
  override def executeSQL(sql: String): List[User] = ???
}

trait UserRepositoryImpl extends UserRepository{
  this: TransactionManager =>
  override def loadUser: List[User] = executeSQL("SELECT * FROM user")
}

trait DefaultUserRepository extends UserRepositoryImpl with DefaultTransactionManager

trait UserServiceImpl extends UserService {
  this: UserRepository =>
  override def findAll: List[User] = loadUser
}

