package hoi4

object Hoi4Helper {

  case class Equipment(name: String, cost: Double)

  val smallArms2 = Equipment("small arms2", 0.60)
  val artillery2 = Equipment("artillery2", 4.0)
  val antiTank1 = Equipment("anti tank gun1", 4.0)
  val antiTank2 = Equipment("anti tank gun2", 5.0)
  val mech = Equipment("mech infantry", 12.0)
  val support = Equipment("support", 4.0)
  val lightArmor2 = Equipment("light armor2", 9.0)
  val lightArmor3 = Equipment("light armor3", 10.0)
  val mediumTank3 = Equipment("medium tank3", 13.0)
  val mediumSelfArtillery3 = Equipment("medium self artillery3", 13.0)
  val lightSelfArtillery2 = Equipment("lightSelfArtillery2", 9.0)
  val lightSelfArtillery3 = Equipment("lightSelfArtillery3", 10.0)
  val selfRocket = Equipment("self rocket", 12.0)
  val motorized = Equipment("motorized", 2.50)
  val f3f = Equipment("f3f", 28.40)

  val equipmentTypes = List(smallArms2, artillery2, antiTank1, support, lightArmor2,
    lightSelfArtillery2, motorized)

  val basicProduct39: Double = 5 * 1.60 * 0.8008

  case class DivisionType(name: String, equipments: Map[Equipment, Int])

  val infantry41 = DivisionType("infantry 41", Map(
    smallArms2 -> 710,
    artillery2 -> 96,
    antiTank1 -> 24,
    support -> 30
  ))
  val marine40 = DivisionType("marine 40", Map(
    smallArms2 -> 1100,
    artillery2 -> 96,
    support -> 40
  ))
  val lightArmor41 = DivisionType("light armor 41", Map(
    lightArmor2 -> 300,
    smallArms2 -> 250,
    antiTank1 -> 24,
    artillery2 -> 24,
    motorized -> 100,
    support -> 40,
    lightSelfArtillery2 -> 72
  ))
  val motorized41 = DivisionType("motorized 41", Map(
    smallArms2 -> 710,
    antiTank1 -> 24,
    artillery2 -> 24,
    motorized -> 380,
    support -> 30,
    selfRocket -> 40
  ))
  val motorized42 = DivisionType("motorized 42", Map(
    smallArms2 -> 710,
    antiTank2 -> 24,
    artillery2 -> 24,
    motorized -> 380,
    support -> 30,
    selfRocket -> 40
  ))
  val tank42 = DivisionType("tank 42", Map(
    antiTank2 -> 24,
    artillery2 -> 24,
    smallArms2 -> 450,
    mech -> 200,
    support -> 40,
    mediumTank3 -> 150,
    mediumSelfArtillery3 -> 72
  ))
  val mech42 = DivisionType("mech 42", Map(
    antiTank2 -> 24,
    artillery2 -> 24,
    selfRocket -> 40,
    smallArms2 -> 710,
    mech -> 350,
    motorized -> 30,
    support -> 30
  ))

  def requiredFactoryToProduceInOneYear(equipment: Equipment, quantity: Int): Double = {
    quantity / (basicProduct39 / equipment.cost * 365)
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

  val f3fCost = 28.40
  val tbdCost = 31.20
  val p40Cost = 21.6
  val a17Cost = 22.0
  val xtbd1Cost = 26
  val f4fCost = 30.4
  val p51Cost = 25.20
  val sbdCost = 21.60
  println(s"to produce 100 f3f, we need ${100 / (basicProduct39 / f3fCost * 365)}")
  println(s"to produce 100 tbd, we need ${100 / (basicProduct39 / tbdCost * 365)}")
  println(s"to produce 100 p40, we need ${100 / (basicProduct39 / p40Cost * 365)}")
  println(s"to produce 100 a17, we need ${100 / (basicProduct39 / a17Cost * 365)}")
  println(s"to produce 100 xtbd1, we need ${100 / (basicProduct39 / xtbd1Cost * 365)}")
  println(s"to produce 100 f4f, we need ${100 / (basicProduct39 / f4fCost * 365)}")
  println(s"to produce 100 p51, we need ${100 / (basicProduct39 / p51Cost * 365)}")
  println(s"to produce 100 sbd, we need ${100 / (basicProduct39 / sbdCost * 365)}")

  val infrastructureCost: Int = 3000
  val airbaseCost = 1250
  val antiAirCost = 2500
  val radarCost = 3375

  val militaryFactoryCost = 7200
  val civilianFactoryCost = 10800
  val shipyardCost = 6400

  val navalBaseCost = 3000
  val fortressCost = 500
  val coastFortressCost = 500

  val constructionAbility4NavalBase = 5 * 1.45
  val constructionAbility4Infrastructure = 5 * 1.65

  val constructionAbility4MilitaryFactory = 5 * 1.80 * 1.5
  val constructionAbility4CivilianFactory = 5 * 1.45 * 1.5
  val constructionAbility4Shipyard = 5 * 1.55 * 1.5

  val constructionNum = 180
  println(s"with $constructionNum constructors, we can construct one of following per year:")
  println(s"navalBase: ${constructionAbility4NavalBase * constructionNum * 365 / navalBaseCost}")
  println(s"infrastructures: ${constructionAbility4Infrastructure * constructionNum * 365 / infrastructureCost}")
  println(s"airbase: ${constructionAbility4Infrastructure * constructionNum * 365 / airbaseCost}")
  println(s"antiAir: ${constructionAbility4Infrastructure * constructionNum * 365 / antiAirCost}")
  println(s"radar: ${constructionAbility4Infrastructure * constructionNum * 365 / radarCost}")
  println(s"fortress: ${constructionAbility4Infrastructure * constructionNum * 365 / fortressCost}")
  println(s"coastFortress: ${constructionAbility4Infrastructure * constructionNum * 365 / coastFortressCost}")

  println(s"militaryFactory: ${constructionAbility4MilitaryFactory * constructionNum * 365 / militaryFactoryCost}")
  println(s"civilianFactoryCost: ${constructionAbility4CivilianFactory * constructionNum * 365 / civilianFactoryCost}")
  println(s"shipyard: ${constructionAbility4Shipyard * constructionNum * 365 / shipyardCost}")
}

object Hoi4HelperApp extends App {
  import Hoi4Helper._

//  val needToProduce = Map(
//    infantry41 -> (16 + 16 + 12),
//    marine40 -> 8,
//    lightArmor41 -> 8,
//    motorized42 -> 32,
//    mech42 -> 8,
//    tank42 -> 8
//  )

  val northEastArmy = Map(
    infantry41 -> 12,
    marine40 -> 4,
    lightArmor41 -> 4,
    tank42 -> 4
  )
  val chinaArmy = Map(
    infantry41 -> 12,
    marine40 -> 4,
    lightArmor41 -> 4,
    tank42 -> 4
  )
  val southAsiaArmy = Map(
    infantry41 -> 12,
    marine40 -> 4,
    lightArmor41 -> 4,
    tank42 -> 4
  )
  val westEuroArmy1 = Map(
    motorized42 -> 12,
    mech42 -> 6,
    tank42 -> 6
  )
  val westEuroArmy2 = Map(
    motorized42 -> 12,
    mech42 -> 6,
    tank42 -> 6
  )
  val westEuroArmy3 = Map(
    motorized42 -> 12,
    mech42 -> 6,
    tank42 -> 6
  )

  import concurrency.akkaStream.MapUtil._
  val needToProduce = mergeMaps(northEastArmy,chinaArmy,southAsiaArmy,westEuroArmy1,westEuroArmy2,westEuroArmy3)(_ + _)


  val title: String = needToProduce.toSet[(DivisionType,Int)].map {
    case (divisionType:DivisionType,quantity:Int) => s"$quantity ${divisionType.name}"
  }.reduce((s1,s2) => s"$s1 and $s2")

  println(s"in order to produce $title divisions")

  planFactories(needToProduce).foreach{ case (equipment, factoryNum) =>
    println(s"$factoryNum $equipment factories")
  }
}
