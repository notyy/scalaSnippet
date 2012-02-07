package sample
import org.specs2._
import org.specs2.matcher.MustThrownMatchers

class AccountAcceptSpec extends Specification with MustThrownMatchers {
  def is =
    "这是Account的设计规范" ^
      "一个账户必须有拥有者，且其金额必须不能是负数" ! newAccount ^
      "金额可以从一个账户转移到另一个账户，转移后的两账户金额总额应该不变" ! transfer ^
      "账户的合法性可以被检查" ! valid

  def newAccount = {
    val account1 = new Account("notyy", 100.00)
    account1.owner must not be none
    account1.balance must be_>(0.00)
  }

  def transfer = {
    val account1 = new Account("notyy", 100.00)
    val account2 = new Account("someOne", 50.00)
    val sum = account1.balance + account2.balance
    Account.transfer(account1, account2, 50)
    account1.balance === 50.00
    account2.balance === 100.00
    val newSum = account1.balance + account2.balance
    sum === newSum
  }

  def valid = pending
}