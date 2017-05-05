package utils

import java.io._

import org.scalatest.{BeforeAndAfter, FunSpec, Matchers}
import patterns.{Path, ReadSupport}
import utils.Combinator._

class CombinatorsSpec extends FunSpec with Matchers with BeforeAndAfter with ReadSupport {
  val input = "src/test/resources/doOverSample.txt"
  val output = "tmp/doOverSampleOut.txt"
  
  before{
    val file = new File(output)
    if(file.exists()) file.delete()
  }
  
  describe("doOver combinator"){
    it("can combine input func, outputFunc and a process func"){
      doOver(input,output)(_.toUpperCase)
      read(Path(output)) shouldBe "HELLO\nWORLD"
    }
  }
}
