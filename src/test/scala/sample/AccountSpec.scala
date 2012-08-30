package sample
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FunSpec

class AccountSpec extends FunSpec with ShouldMatchers {
  describe("An Account") {
    it("should have owner when initialized，and balance >= 0") {
      val account = new Account("notyy", 100)
      account.owner should not be (null)
      account.balance should be > (0.00)
    }

    it("amount can be transfer between accounts,total balance should be same after transfer") {
      val accountA = new Account("A", 100.00)
      val accountB = new Account("B", 50.00)
      val sum = accountA.balance + accountB.balance
      Account.transfer(accountA, accountB, 50)
      accountA.balance should be === 50.00
      accountB.balance should be === 100.00
      val newSum = accountA.balance + accountB.balance
      sum should be === newSum
    }

    it("Legality of account can be checked")(pending)
  }
}
