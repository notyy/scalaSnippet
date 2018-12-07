package hoi4

import org.scalatest.{FunSpec, Matchers}
import Hoi4Helper._

class Hoi4HelperTest extends FunSpec with Matchers {

  val infantry37 = DivisionType("infantry37", Map(
    smallArms2 -> 750,
    artillery2 -> 84,
    support -> 40
  ))

  val motorized41 = DivisionType("motorized41", Map(
    smallArms2 -> 710,
    artillery2 -> 12,
    motorized -> 410,
    support -> 80,
    selfRocket -> 40
  ))

  describe("Hoi4Helper") {
    it("can calc how many equipment it needs for a given number of divisions") {
      val equipments = divisionEquiptments(infantry37, 2)
      equipments should have size 3
      equipments(smallArms2) shouldBe 1500
      equipments(artillery2) shouldBe 168
      equipments(support) shouldBe 80
    }
    it("can calc total equipments requirement by equipment types") {
      val division1Equipments: Map[Equipment, Int] = infantry37.equipments
      val division2Equipments: Map[Equipment, Int] = motorized41.equipments
      val total = totalEquipments(List(division1Equipments,division2Equipments))
      total should have size 5
      total(smallArms2) shouldBe 1460
      total(artillery2) shouldBe 96
      total(motorized) shouldBe 410
      total(selfRocket) shouldBe 40
      total(support) shouldBe 120
    }
    it("can calc required factory number for a given equipment to be produce in one year"){
      requiredFactoryToProduceInOneYear(carrierFighter1, 100) shouldBe 0.8908428020101175
    }
  }
}
