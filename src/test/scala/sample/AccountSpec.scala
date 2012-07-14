package sample
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FunSpec

class AccountSpec extends FunSpec with ShouldMatchers {
  describe("一个账户") {
    it("初始化的时候应该有所有者，并且余额大于0") {
      val account = new Account("notyy", 100)
      account.owner should not be (null)
      account.balance should be > (0.00)
    }

    it("金额可以从一个账户转移到另一个账户，转移后的两账户金额总额应该不变:") {
      val accountA = new Account("A", 100.00)
      val accountB = new Account("B", 50.00)
      val sum = accountA.balance + accountB.balance
      Account.transfer(accountA, accountB, 50)
      accountA.balance should be === 50.00
      accountB.balance should be === 100.00
      val newSum = accountA.balance + accountB.balance
      sum should be === newSum
    }

    it("账户的合法性可以被检查")(pending)
  }
}
