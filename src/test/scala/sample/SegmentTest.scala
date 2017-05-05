package sample

import org.scalatest.{FunSpec, Matchers}
import sample.Segment._

class SegmentTest extends FunSpec with Matchers {
   describe("segment"){
     it("can be expressed by UnOp"){
       val expr = 5 < "EcIo"
       val expr1 = 5 < "EcIo" < 10
       expr1 match {
         case (Value(left) < Variable(name)) => println(s"$left < $name")
         case (Value(left) < Variable(name) < Value(right)) => println(s"$left < $name < $right")
         case _ => println("don't know")
       }
     }
     it("expression can be regexed"){
       val BinOpLT = """(\d+)(<|<=|=)(\w+)""".r
       val Between = """(\d+)(<|<=|=)(\w+)(<|<=|=)(\d+)""".r
       val BinOpGT = """(\w+)(>|>=|=)(\d+)""".r

       val expr1 = "5<EcIo<10"
       expr1 match {
         case BinOpLT(left,op,variable) => println(s"$left $op $variable")
         case Between(left,op1,variable, op2, right) => println(s"$left $op1 $variable $op2 $right")
         case BinOpGT(variable, op, right) => println(s"$variable $op $right")
         case _ => println(s"can't parse expression:$expr1")
       }
     }
 //    it("a list can be pattern matching"){
 //      val list = List(1,2,3)
 //      list match {
 //        case 1:: 2:: x :: Nil => println(s"x=$x")
 //        case _ => println("nothing")
 //      }
 //    }
   }
 }
