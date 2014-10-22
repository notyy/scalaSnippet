package concurrency.akka.account

import akka.actor.{Actor, ActorLogging}

import scala.collection.mutable

case class Create(name: String, balance: Double)

//开户，暂时以name为key，所以不能重复
case class Destroy(name: String)

//销户
case class Increase(name: String, amount: Double)

case class Decrease(name: String, amount: Double)

case class Query(name: String)

case object Report

class AccountAssistant extends Actor with ActorLogging{
  var accounts: mutable.Map[String, Double] = mutable.Map()
  override def receive: Receive = {
    case Create(name,balance) => accounts += (name -> balance)
    case Destroy(name) => accounts -= name
    case Increase(name,amount) => accounts.update(name, accounts(name) + amount)
    case Decrease(name,amount) => accounts.update(name, accounts(name) - amount)
    case Query(name) => log.info(s"balance in $name is ${accounts(name)}")
    case Report => {
      log.info(s"accounts informations:")
      accounts.foreach{
        case (name, balance) => log.info(s" $name $balance")
      }
    }
  }
}
