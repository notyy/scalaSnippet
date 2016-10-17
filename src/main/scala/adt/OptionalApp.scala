package adt

import adt.OptionalModule.{Just, NotExist, Optional}

object OptionalApp extends App {

  case class User(name: String, age: Int, email: String)
  case class Address(road: String)

  def getUserByNameSimple: String => User = ???

  def getUserByName: String => Optional[User] = {
    case "damotou" => Just(User("damotou", 39, "notyycn@gmail.com"))
    case _ => NotExist
  }

  def getAge: User => Int = _.age

  def fetchGetAgeFunc: Optional[User => Int] = Just((user:User) => user.age)

  def getEmail: User => Optional[String] = ???
  def getAddress: String => Optional[Address] = ???

  getUserByName("damotou").map(getAge)

  def lift[A,B]: (A => B) => (Optional[A] => Optional[B]) = f =>  {
    case Just(v) => Just(f(v))
    case NotExist => NotExist
  }

  lift(getAge)(getUserByName("damotou"))

  val address:Optional[Address] = getUserByName("damotou").flatMap(getEmail).flatMap(getAddress)
  val address1:Optional[Address] = for{
    user <- getUserByName("damotou")
    email <- getEmail(user)
    address <- getAddress(email)
  } yield address


//    getUserByName("damotou") match {
//    case Just(user) => getEmail(user) match {
//      case Just(email) => getAddress(email)
//      case NotExist => NotExist
//    }
//    case NotExist => NotExist
//  }

//  getUserByName("damotou") match {
//    case Just(user) => fetchGetAgeFunc match {
//      case Just(func) => Just(func(user))
//      case NotExist => NotExist
//    }
//    case NotExist => NotExist
//  }
  getUserByName("damotou") <%> fetchGetAgeFunc

}
