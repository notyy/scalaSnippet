package utils

import java.io.PrintWriter

import org.scalatest.{BeforeAndAfter, ShouldMatchers, FunSpec}
import patterns.{Path, ReadSupport}
import Combinators._
import java.io._

class CombinatorsSpec extends FunSpec with ShouldMatchers with BeforeAndAfter with ReadSupport {
  val input = "src/test/resources/doOverSample.txt"
  val output = "src/test/resources/doOverSampleOut.txt"
  
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
