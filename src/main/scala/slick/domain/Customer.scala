package slick.domain

case class Customer(id: Option[String], name: String, birthday: Option[String])
case class Product(id: Option[String], name: String)
case class Trade(id: Option[String], customer: Customer, product: Product)