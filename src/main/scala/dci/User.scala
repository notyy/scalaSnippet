package dci

sealed trait User

case class AnonymousUser(tempId: String) extends User
private[dci] case class RegisteredUser(id: String, name: String) extends User
case class SuperUser(id: String, powerLevel: Int, scope: String) extends User

trait ShoppingCart

trait UserInfo