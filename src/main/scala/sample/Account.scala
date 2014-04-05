package sample
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Account(val owner: String, var balance: Double)

object Account {
  val logger = LoggerFactory.getLogger(Account.getClass())
  def transfer(from: Account, to: Account, amount: Double) = {
    logger.info(s"tranfering from ${from.owner} to=${to.owner}") //TODO: more than two argument needs java array
    from.balance -= amount
    to.balance += amount
    logger.info(s"tranfer of amount $amount completed")
  }

  def credit(to: Account, amount: Double) { to.balance += amount }

  def valid(account: Account): Boolean = false //TODO: waiting to be implemented
}
