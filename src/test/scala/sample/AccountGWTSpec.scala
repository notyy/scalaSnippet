package sample
import org.specs2.Specification
import org.specs2.matcher.MustThrownMatchers
import org.specs2.specification.Given
import org.specs2.specification.When
import org.specs2.specification.Then
import org.specs2.execute.Result

class AccountGWTSpec extends Specification with MustThrownMatchers {
  def is =
    "这是Account的设计规范" ^
      p ^
      "以下是转账的例子" ^
      "Given 账户A，拥有者${A}, 金额${100}" ^ accountA ^
      "And 账户B, 拥有者${B}, 金额${50}" ^ accountB ^
      "When 从账户A中转账${50}到账户B" ^ transfer ^
      "Then 账户A剩余金额${50},账户B剩余金额${100}" ^ transferResult ^
      end

  object accountA extends Given[Account] {
    def extract(text: String): Account = {
      val t = extract2(text)
      new Account(t._1, t._2.toDouble)
    }
  }

  object accountB extends When[Account, (Account, Account)] {
    def extract(accountA: Account, text: String): (Account, Account) = {
      val t = extract2(text)
      val accountB = new Account(t._1, t._2.toDouble)
      (accountA, accountB)
    }
  }

  object transfer extends When[(Account, Account), (Account, Account)] {
    def extract(accounts: (Account, Account), text: String): (Account, Account) = {
      val amount = extract1(text).toDouble
      accounts match {
        case (accountA, accountB) => Account.transfer(accountA, accountB, amount); (accountA, accountB)
      }
    }
  }

  object transferResult extends Then[(Account, Account)] {
    def extract(accounts: (Account, Account), text: String): Result = {
      val t = extract2(text)
      val expA = t._1.toDouble
      val expB = t._2.toDouble
      accounts match {
        case (accountA, accountB) => accountA.balance === expA && accountB.balance === expB
      }
    }
  }
}