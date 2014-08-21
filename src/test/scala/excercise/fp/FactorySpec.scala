package excercise.fp

import excercise.fp.Factory._
import org.scalatest.tags.Slow
import org.scalatest.{FunSpec, ShouldMatchers}
import org.slf4j.LoggerFactory
import tags.Tags.{FastTest, DbTest}

@Slow
class FactorySpec extends FunSpec with ShouldMatchers {
  val logger = LoggerFactory.getLogger(this.getClass)

  describe("object Factory defines many functions") {
    describe("createPlan") {
      it("should create order processing plan" +
        " for given ProductionResource and OrderRequest",DbTest) {
        val request = """G:	2
                        |M:	1
                        |R:	1
                        |P:	1
                        |Order: zteT zteW zteX""".stripMargin('|').lines.toList
        val expectedResult = """Order＃1
                               |G1:	 	zteT	zteW
                               |G2:		zteX
                               |M：	zteX 	zteW	zteT
                               |R：		zteX 	zteW	zteT
                               |P：		zteX 	zteW	zteT
                               |Total: 17 hours""".stripMargin('|').lines.toList
        pending
        createPlan(request) shouldBe expectedResult
      }

      describe("parseResourceDesc") {
        it("can parse G: 2 to (G,2)") {
          parseResourceDesc("G:  2") shouldBe(GMachine, Quantity(2))
        }
      }

      describe("initializeMachine") {
        it("can convert machineType&quantity to machine instance",FastTest) {
          val rd: ResourceDesc = (GMachine, Quantity(2))
          val machines = initializeMachine(rd)
          machines.size shouldBe 2
          machines(0).machine.id shouldBe 1
          machines(0).products shouldBe empty
          machines(1).machine.id shouldBe 2
          machines(1).products shouldBe empty
        }
      }
      describe("arrangeProduction") {
        it("can assign product to machine") {
          val workProcesses: List[WorkProcess] = List(
            WorkProcess(Machine(GMachine, 1), Nil),
            WorkProcess(Machine(GMachine, 2), Nil),
            WorkProcess(Machine(MMachine, 1), Nil),
            WorkProcess(Machine(RMachine, 1), Nil),
            WorkProcess(Machine(PMachine, 1), Nil)
          )

          val zteTArranged = arrangeProduction(workProcesses)(Product("zteT"))
          zteTArranged(0).products shouldBe List(Product("zteT"))
          zteTArranged(1).products shouldBe Nil
          zteTArranged(2).products shouldBe List(Product("zteT"))
          zteTArranged(3).products shouldBe List(Product("zteT"))
          zteTArranged(4).products shouldBe List(Product("zteT"))

          val zteWArranged = arrangeProduction(zteTArranged)(Product("zteW"))
          zteWArranged.foreach(x => logger.info(x.toString))
          zteWArranged(0).products shouldBe List(Product("zteT"))
          zteWArranged(1).products shouldBe List(Product("zteW"))
          zteWArranged(2).products shouldBe List(Product("zteT"), Product("zteW"))
          zteWArranged(3).products shouldBe List(Product("zteT"), Product("zteW"))
          zteWArranged(4).products shouldBe List(Product("zteT"), Product("zteW"))
        }
      }

      describe("formatShowPlan"){
        it("should pretty print a plan"){

          val workProcess = List(
            WorkProcess(Machine(GMachine, 1), List(Product("zteT"))),
            WorkProcess(Machine(GMachine, 2), List(Product("zteW"))),
            WorkProcess(Machine(MMachine, 1), List(Product("zteT"), Product("zteW"))),
            WorkProcess(Machine(PMachine, 1), List(Product("zteT"), Product("zteW"))),
            WorkProcess(Machine(RMachine, 1), List(Product("zteT"), Product("zteW"))))
          val plan = Plan(1, workProcess)
          logger.warn(formatShowPlan(plan))
        }
      }
    }
  }
}
