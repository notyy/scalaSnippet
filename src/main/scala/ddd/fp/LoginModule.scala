package ddd.fp

object LoginModule {
  case class Email(value: String)

  //TODO how to make this private?
  case class VerifiedEmail(email: Email)

  case class User(name: String, email: VerifiedEmail)

  def verify: Email => VerifiedEmail = ???

  def login: VerifiedEmail => User = ???
}
