package excercise.fp

import org.slf4j.LoggerFactory

import scala.io.Source

object Factory {
  val logger = LoggerFactory.getLogger(Factory.getClass)

  case class FileName(value: String) extends AnyVal

  def processInput: FileName => Unit = fileName => {
    val request = Source.fromFile(fileName.value).getLines().toList
    createPlan(request).map(formatShowPlan).foreach(println)
  }

  case class Plan(orderId: Int, workProcesses: List[WorkProcess])
  case class Product(value: String) extends AnyVal

  def createPlan: List[String] => List[Plan] = requestDesc => {
    val (resources, orders) = parseRequest(requestDesc)
    orders.map(naiveCreatePlan(resources))
  }

  case class Order(id: Int, products: List[Product])
  type ResourceDesc = (MachineType, Quantity)
  trait MachineType
  case object GMachine extends MachineType
  case object MMachine extends MachineType
  case object RMachine extends MachineType
  case object PMachine extends MachineType
  case object Cooler extends MachineType

  case class Quantity(value: Int) extends AnyVal

  def parseRequest: List[String] => (List[ResourceDesc], List[Order]) = {
    splitRequest andThen transformRequest.tupled
  }

  def splitRequest: List[String] => (List[String], List[String]) = src =>{
    src.splitAt(src.indexWhere(_.startsWith("Order:")))
  }

  def transformRequest: (List[String], List[String]) => (List[ResourceDesc], List[Order]) =
    (resourceDescStr, orderStrs) => {
      (resourceDescStr.map(parseResourceDesc), orderStrs.zipWithIndex.map {
        case (order, id) => parseOrderDesc(id)(order)
      })
    }

  def parseOrderDesc: Int => String => Order = orderId => str => {
    Order(orderId, str.split(":").last.trim.split(" ").toList.map(Product))
  }

  def parseResourceDesc: String => ResourceDesc = s => {
    val splitted = s.split(":")
    (strToMachineType(splitted.head), Quantity(splitted.last.trim.toInt))
  }

  def naiveCreatePlan: List[ResourceDesc] => Order => Plan = rDesc => order => {
    val workProcesses = rDesc.flatMap(initializeMachine)
    naiveCreatePlanFromWorkProcess(workProcesses)(order)
  }

  case class WorkProcess(machine: Machine, products: List[Product])
  case class Machine(mType: MachineType, id: Int)

  def initializeMachine: ResourceDesc => List[WorkProcess] = {
    case (machineType, quantity) => (1 to quantity.value).map(i =>
      WorkProcess(Machine(machineType, i), Nil)).toList
  }

  def naiveCreatePlanFromWorkProcess: List[WorkProcess] => Order => Plan = workProcesses => order => {
    Plan(order.id, arrange(arrangeProduction)(workProcesses)(order.products))
  }

  def arrange: ArrangeFunc => List[WorkProcess] => List[Product] => List[WorkProcess] =
    f => workProcesses => products => {
      products.foldLeft(workProcesses)((wps, product) => f(wps)(product))
    }

  type ArrangeFunc = List[WorkProcess] => Product => List[WorkProcess]

  def arrangeProduction: ArrangeFunc = workProcesses => product => {
    workProcesses.groupBy(_.machine.mType).values.map(
      workProcessByType => assignProduct(workProcessByType, product)).
        flatten.toList.sortBy(_.machine.toString)
  }

  def assignProduct(processes: List[WorkProcess], product: Product): List[WorkProcess] = {
    val sorted = processes.sortBy(_.products.size)
    val idlest: WorkProcess = sorted(0)
    sorted.updated(0, WorkProcess(idlest.machine, idlest.products :+ product))
  }

  def formatShowPlan: Plan => String = plan => {
    s"Order #${plan.orderId}\n" +
      plan.workProcesses.map(w => s"${machine2Str(w.machine)}: ${w.products.map(_.value).mkString("\t")}").mkString("\n") + "\n" +
      s"Total: ${calcTime(productMachineTime)(plan)} hours"
  }

  val machineTypeStrMap: Map[String, MachineType] = Map("G" -> GMachine, "M" -> MMachine, "R" -> RMachine, "P" -> PMachine)

  def strToMachineType: String => MachineType = machineTypeStrMap(_)

  def machine2Str: Machine => String = m => s"${m.mType.toString.head}${m.id}"

  type Hour = Int
  type ProductMachineTime = Map[Product, Map[MachineType, Hour]]
  val productMachineTime: ProductMachineTime = Map(
    Product("zteT") -> Map(GMachine -> 3, MMachine -> 3, RMachine -> 1, Cooler -> 2, PMachine -> 1),
    Product("zteW") -> Map(GMachine -> 2, MMachine -> 2, RMachine -> 3, Cooler -> 2, PMachine -> 2),
    Product("zteX") -> Map(GMachine -> 3, MMachine -> 3, RMachine -> 1, Cooler -> 5, PMachine -> 1),
    Product("zteY") -> Map(GMachine -> 1, MMachine -> 1, RMachine -> 4, Cooler -> 3, PMachine -> 3),
    Product("zteZ") -> Map(GMachine -> 2, MMachine -> 3, RMachine -> 2, Cooler -> 1, PMachine -> 1)
  )

  def calcTime: ProductMachineTime => Plan => Int = pmTime => plan => {
    (for (w <- plan.workProcesses; p <- w.products) yield pmTime(p)(w.machine.mType)).sum
  }
}