package dci

case class UserName(value: String) extends AnyVal
case class Password(value: String) extends AnyVal
case class Email(value: String) extends AnyVal
case class Address(value: String) extends AnyVal

object AccountService {
  def openAccount(user: User): Account = ???

  def register1:(UserInfo, Set[RegisteredUser]) => (RegisteredUser, Set[RegisteredUser]) = ???

  def register(name: String, password: String, email: String, address: String):RegisteredUser = ???

  def register: (UserName, Password, Email, Address) => RegisteredUser = ???
}
