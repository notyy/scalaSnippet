package sample
import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.prop.GeneratorDrivenPropertyChecks

class AccountScalaCheckSpec extends FeatureSpec with GivenWhenThen with ShouldMatchers with GeneratorDrivenPropertyChecks {
  feature("Account can be created,checked,and transfer with each other") {
    info("As Account Manager")
    info("I want account system can make sure money in accounts be transfered safely，account status can be checked")
    info("to ensure the stable and accurate of whole accunting system")

    scenario("Account can be created,Account must have owner and initialization amount must be > 0")(pending)
    scenario("money can transfer between account") {
      given("Account A，amount > 0")
      and("Account B，amount > 0")
      when("transfer from account A to account B,amount < balance of account A")
      then("amount in account A = init amount - transfer amount")
      and("amount in account B = init amount + transfer amount")
      and("amount in account A and account are both >= 0")
      forAll("balanceA", "balanceB", "transferAmount", minSuccessful(50), maxDiscarded(5000)) {
        (balanceA: Double, balanceB: Double, transferAmount: Double) =>
          whenever(balanceA > 0.00 && balanceB > 0.00
            && transferAmount <= balanceA && transferAmount >= 0.00) {
            val accountA = new Account("A", balanceA)
            val accountB = new Account("B", balanceB)
            Account.transfer(accountA, accountB, transferAmount)
            accountA.balance should be === balanceA - transferAmount
            accountB.balance should be === balanceB + transferAmount
            accountA.balance should be >= 0.00
            accountB.balance should be >= 0.00
          }
      }
    }
    scenario("amount can transfer between account, but transfer amount can't > it's balance")(pending)
  }
}