package hoi4

object Hoi4Helper {

  case class Equipment(name: String, cost: Double)

  val smallArms2 = Equipment("small arms2", 0.60)
  val artillery2 = Equipment("artillery2", 4.0)
  val antiTank1 = Equipment("anti tank gun1", 4.0)
  val antiTank2 = Equipment("anti tank gun2", 5.0)
  val mech = Equipment("mech infantry1", 8.0)
  val support = Equipment("support", 4.0)
  val lightArmor2 = Equipment("light armor2", 9.0)
  val lightArmor3 = Equipment("light armor3", 10.0)
  val mediumTank1 = Equipment("medium tank1", 12.0)
  val mediumTank3 = Equipment("medium tank3", 13.0)
  val mediumSelfArtillery1 = Equipment("medium self artillery1", 12.0)
  val mediumSelfArtillery3 = Equipment("medium self artillery3", 13.0)
  val lightSelfArtillery2 = Equipment("lightSelfArtillery2", 9.0)
  val lightSelfArtillery3 = Equipment("lightSelfArtillery3", 10.0)
  val selfRocket = Equipment("self rocket", 12.0)
  val motorized = Equipment("motorized", 2.50)
  val carrierFighter1 = Equipment("carrierFighter1", 28.40)
  val carrierFighter2 = Equipment("carrierFighter2", 30.40)

  val basicProduct: Double = 5 * (1+ 0.15 + 0.189 + 0.60) * 0.9009

  case class DivisionType(name: String, equipments: Map[Equipment, Int])

  def requiredFactoryToProduceInOneYear(equipment: Equipment, quantity: Int): Double = {
    quantity / (basicProduct / equipment.cost * 365)
  }

  def planFactories(divisions: Map[DivisionType, Int]): Map[String, Double] = {
    totalEquipments(divisions.foldLeft(List[Map[Equipment, Int]]()) { case (acc, (k, v)) =>
      divisionEquiptments(k, v) :: acc
    }).map {
      case (equipment, quantity) => equipment.name -> requiredFactoryToProduceInOneYear(equipment, quantity)
    }
  }

  def totalEquipments(equipments: Seq[Map[Equipment, Int]]): Map[Equipment, Int] = {
    equipments.foldLeft(Map[Equipment, Int]()) { (acc, partEquipments) =>
      partEquipments.foldLeft(acc) { case (outerAcc, (equipment, num)) =>
        if (outerAcc.contains(equipment)) {
          outerAcc.updated(equipment, outerAcc(equipment) + num)
        } else {
          outerAcc.updated(equipment, num)
        }
      }
    }
  }

  def divisionEquiptments(divisionType: DivisionType, howManyDivisions: Int): Map[Equipment, Int] = {
    divisionType.equipments.mapValues(_ * howManyDivisions)
  }

}

object Hoi4HelperApp extends App {
  import Hoi4Helper._

  //define Division types
  val infantry37 = DivisionType("infantry37", Map(
    smallArms2 -> 750,
    artillery2 -> 84,
    support -> 40
  ))
  val marine40 = DivisionType("marine", Map(
    smallArms2 -> 1100,
    artillery2 -> 84,
    support -> 70,
    motorized -> 20
  ))
  val motorized41 = DivisionType("motorized41", Map(
    smallArms2 -> 710,
    artillery2 -> 12,
    motorized -> 410,
    support -> 80,
    selfRocket -> 40
  ))
  val tank40 = DivisionType("tank40", Map(
    artillery2 -> 12,
    smallArms2 -> 410,
    motorized -> 210,
    support -> 50,
    mediumTank1 -> 150,
    mediumSelfArtillery1 -> 72
  ))

  //define armies
  val asiaDefendArmy = Map(
    infantry37 -> 12,
    marine40 -> 3,
    tank40 -> 4,
    motorized41 -> 5
  )

  val japanArmy = Map(
    tank40 -> 8,
    motorized41 -> 16
  )

  import concurrency.akkaStream.MapUtil._
  val needToProduce = mergeMaps(asiaDefendArmy,japanArmy)(_ + _)

  val title: String = needToProduce.toSet[(DivisionType,Int)].map {
    case (divisionType:DivisionType,quantity:Int) => s"$quantity ${divisionType.name}"
  }.reduce((s1,s2) => s"$s1 and $s2")

  println(s"in order to produce $title divisions")

  planFactories(needToProduce).foreach{ case (equipment, factoryNum) =>
    println(s"$factoryNum $equipment factories")
  }
}
