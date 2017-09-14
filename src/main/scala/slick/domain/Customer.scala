package slick.domain

import java.util.Date

case class AuditInfo (
  created: Option[Date] = None,
  createdBy: Option[String] = None,
  updated: Option[Date] = null,
  updatedBy: Option[String] = None
)

sealed trait UserType
case object SuperUser extends UserType
case object CommonUser extends UserType

case class Customer(id: Option[String], name: String, birthday: Option[String], userType: UserType, auditInfo: AuditInfo)
case class Product(id: Option[String], name: String)
case class Trade(id: Option[String], customer: Customer, product: Product)