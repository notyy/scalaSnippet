package adt

import adt.OptionalModule.{Just, NotExist, Optional}

object OptionalApp extends App {

  case class User(name: String, age: Int, email: String)

  def getUserByNameSimple: String => User = ???

  def getUserByName: String => Optional[User] = {
    case "damotou" => Just(User("damotou", 39, "notyycn@gmail.com"))
    case _ => NotExist
  }

  def getAge: User => Int = _.age

  def fetchGetAgeFunc: Optional[User => Int] = Just((user:User) => user.age)

  getUserByName("damotou").map(getAge)

  getUserByName("damotou") match {
    case Just(user) => fetchGetAgeFunc match {
      case Just(func) => Just(func(user))
      case NotExist => NotExist
    }
    case NotExist => NotExist
  }
}
