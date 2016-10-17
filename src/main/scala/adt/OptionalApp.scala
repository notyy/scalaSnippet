package adt

import adt.OptionalModule.{Just, NotExist, Optional}

object OptionalApp extends App {

  case class User(name: String, age: Int, email: String)

  def getUserByName: String => Optional[User] = {
    case "damotou" => Just(User("damotou", 39, "notyycn@gmail.com"))
    case _ => NotExist
  }

  def getAge: User => Int = _.age
}
