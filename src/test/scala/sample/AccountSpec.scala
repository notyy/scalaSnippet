package sample
import org.specs2.mutable._
import org.specs2.matcher.MustThrownMatchers

class AccountSpec extends Specification with MustThrownMatchers {
  val account1 = new Account("notyy", 100.00)
  val account2 = new Account("someOne", 50.00)

  "an Account should have owner and positive balance" in {
    account1.owner must not be none
    account1.balance must be_>(0.00)
  }

  "金额可以从一个账户转移到另一个账户，转移后的两账户金额总额应该不变" in {
    val sum = account1.balance + account2.balance
    Account.transfer(account1, account2, 50)
    account1.balance === 50.00
    account2.balance === 100.00
    val newSum = account1.balance + account2.balance
    sum === newSum
  }
}