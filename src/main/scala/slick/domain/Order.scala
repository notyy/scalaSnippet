package slick.domain

import java.util.Date

case class AuditInfo (
  created: Option[Date] = None,
  createdBy: Option[String] = None,
  updated: Option[Date] = None,
  updatedBy: Option[String] = None
)

sealed trait UserType
case object SuperUser extends UserType
case object CommonUser extends UserType

case class Customer(id: Option[String], name: String, birthday: Option[String], userType: UserType, auditInfo: AuditInfo)
case class Product(id: Option[String], name: String)
// for demo reason, only allow 1 product 1 customer in 1 order
case class Order(id: Option[String], customer: Customer, product: Product)