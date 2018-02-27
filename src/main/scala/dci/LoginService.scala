package dci

object LoginService {
  def login(userName: String, password: String): RegisteredUser = RegisteredUser("1000","yy")
}

object CrazyShopping {
  def buybuybuy(user:RegisteredUser,product: Product): ShoppingCart = ???

  def destroyIt(user: User, product: Product): Unit = user match {
    case AnonymousUser(_) => println("you can't!")
    case RegisteredUser(_,name) => print(s"you can destroy your own $product")
    case SuperUser(_,powerLevel,_) if powerLevel > 3 => print(s"done and done")
    case _ => println("who are you?")
  }

}
