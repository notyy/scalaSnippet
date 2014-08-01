package monad

import java.io.File

import monad.StringPresentableMonad.Account
import org.scalatest.{BeforeAndAfter, FunSpec, ShouldMatchers}

import scala.io.Source

class PersistedTest extends FunSpec with ShouldMatchers with BeforeAndAfter {

  val path = FilePath("/Users/twer/temp/test.txt")
  val content = "hello,world\n"



  before {
    new File(path.value).delete()
  }

  describe("Persisted monad") {
    it("can put value inside persisted context, value will be saved to given file path") {
      Persisted(path, content) shouldBe PersistedImpl(path, content)
      Source.fromFile(path.value).getLines().toList should contain("hello,world")
    }
    it("can take value out of context") {
      Persisted(path, content).get shouldBe content
    }
    it("can change value inside persisted context without take it out") {
      Persisted(path, content).map(_.toUpperCase).get shouldBe content.toUpperCase
      Source.fromFile(path.value).getLines().toList should not contain "hello,world"
      Source.fromFile(path.value).getLines().toList should contain("HELLO,WORLD")
    }
    it("can persist any type T, as long as T is StringPresentable") {
      val persistedAccount = Persisted(path, Account("yy",500.0))
      Source.fromFile(path.value).getLines().toList should contain("yy,500.0")
    }
    it("can read back object from file"){
      val persistedAccount = Persisted(path, Account("yy",500.0))
      Persisted.read[Account](path) shouldBe persistedAccount.get
    }
//    it("can write and read back list of objects"){
//      import monad.StringPresentableMonad._
//      val persistedAccounts = Persisted(path, List(Account("xx",200.0), Account("yy", 300.0)))(new ListIsStringPresentable[Account]())
//      Source.fromFile(path.value).getLines().toList should contain("xx,200.0")
//      Source.fromFile(path.value).getLines().toList should contain("yy,300.0")
//      val readValues: List[Account] = Persisted.read[List[Account]](path)
//      readValues should contain(Account("xx",200.0))
//      readValues should contain(Account("yy",300.0))
//    }
  }
}
