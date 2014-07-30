package monad

import java.io.File
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
  }
}
