package hoi4

import org.scalatest.{FunSpec, Matchers}
import Hoi4Helper._

class Hoi4HelperTest extends FunSpec with Matchers {
  describe("Hoi4Helper") {
    it("can calc how many equipment it needs for a given number of divisions") {
      val equipments = divisionEquiptments(infantry41, 2)
      equipments should have size 4
      equipments(smallArms2) shouldBe 1420
      equipments(artillery2) shouldBe 192
      equipments(antiTank1) shouldBe 48
      equipments(support) shouldBe 60
    }
    it("can calc total equipments requirement by equipment types") {
      val division1Equipments: Map[Equipment, Int] = infantry41.equipments
      val division2Equipments: Map[Equipment, Int] = lightArmor41.equipments
      val total = totalEquipments(List(division1Equipments,division2Equipments))
      total should have size 7
      total(smallArms2) shouldBe 960
      total(artillery2) shouldBe 120
      total(lightArmor2) shouldBe 300
      total(motorized) shouldBe 100
      total(lightSelfArtillery2) shouldBe 72
    }
    it("can calc required factory number for a given equipment to be produce in one year"){
      requiredFactoryToProduceInOneYear(f3f, 100) shouldBe 1.2145388857717625
    }
  }
}
