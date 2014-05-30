package sample
import org.scalatest.{Matchers, FeatureSpec, GivenWhenThen}

class AccountAcceptSpec extends FeatureSpec with GivenWhenThen with Matchers {
  feature("Account can be created,checked,and transfer with each other") {
    info("As Account Manager")
    info("I want account system can make sure money in accounts be transfered safely,account status can be checked")
    info("to ensure the stable and accurate of whole accounting system")

    scenario("Account can be created,Account must have owner and initialization amount must be > 0")(pending)
    scenario("money can transfer between accounts") {
      Given("Account A,amount 100")
      val accountA = new Account("A", 100.00)
      And("Account B,amount 50")
      val accountB = new Account("B", 50.00)
      When("transfer 50 from account A to account B")
      Account.transfer(accountA, accountB, 50.00)
      Then("amount in account A becomes 50")
      accountA.balance should be (50)
      And("amount in account B becomes 100")
      accountB.balance should be (100)
    }

    scenario("can not transfer more money than balance of the account")(pending)
  }
}