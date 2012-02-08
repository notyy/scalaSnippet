package sample
import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.ShouldMatchers

class AccountAcceptSpec extends FeatureSpec with GivenWhenThen with ShouldMatchers {
  feature("账户可以被创建，检查和相互转账") {
    info("作为账户体系管理者")
    info("我要求账户体系能够确保账户内的资金可以可靠的流转，账户状态可以被检查")
    info("以确保整个账户体系的稳固和准确")

    scenario("账户可以被创建，账户必须有所有者，且初始化为大于0的值")(pending)
    scenario("账户可以相互转账") {
      given("账户A，金额100")
      and("账户B，金额50")
      when("从账户A中转账50元到账户B")
      then("账户A的金额变为50")
      and("账户B的金额变为100")
      pending
    }
  }
}