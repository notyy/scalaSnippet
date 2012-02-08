package sample
import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.prop.GeneratorDrivenPropertyChecks

class AccountScalaCheckSpec extends FeatureSpec with GivenWhenThen with ShouldMatchers with GeneratorDrivenPropertyChecks {
  feature("账户可以被创建，检查和相互转账") {
    info("作为账户体系管理者")
    info("我要求账户体系能够确保账户内的资金可以可靠的流转，账户状态可以被检查")
    info("以确保整个账户体系的稳固和准确")

    scenario("账户可以被创建，账户必须有所有者，且初始化为大于0的值")(pending)
    scenario("账户可以相互转账") {
      given("账户A，金额>0")
      and("账户B，金额>0")
      when("从账户A中转账<账户余额的金额到账户B")
      then("账户A的金额=初始金额-转账金额")
      and("账户B的金额=初始金额+转账金额")
      and("账户A和账户B的金额都>=0")
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
    scenario("账户可以相互转账，但是转账金额不能大于转出账户余额")(pending)
  }
}