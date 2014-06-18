package excercise.fp

import scala.io.Source

object Factory {
  case class FileName(value: String) extends AnyVal

  def processInput: FileName => Unit = fileName => {
    val request: List[String] = Source.fromFile(fileName.value).getLines().toList
    createPlan(request).map(formatShowPlan).foreach(println)
  }

  def createPlan: List[String] => List[Plan] = requestDesc => {
    val (resources, orders) = parseRequest(requestDesc)
    orders.map(naiveCreatePlan(resources))
  }

  trait MachineType
  case object GMachine extends MachineType
  case object MMachine extends MachineType
  case object RMachine extends MachineType
  case object PMachine extends MachineType
  case object Cooler extends MachineType

  type Quantity = Int
  type Product = String
  type ResourceDesc = (MachineType, Quantity)

  case class Order(id: Int, products: List[Product])

  case class Machine(mType: MachineType,id: Int)
  case class Plan(orderId: Int, workProcess: List[WorkProcess])

  case class WorkProcess(machine: Machine, products: List[Product])

  def parseRequest: List[String] => (List[ResourceDesc], List[Order]) = src => {
    val index = src.indexWhere(_.startsWith("Order:"))
    val (resourceDescStr, orderStrs) = src.splitAt(index)
    (resourceDescStr.map(parseResourceDesc), orderStrs.zipWithIndex.map{case (order, id) => parseOrderDesc(id)(order)})
  }

  def parseResourceDesc: String => ResourceDesc = s => {
    val splitted = s.split(":")
    (strToMachineType(splitted.head), splitted.last.trim.toInt)
  }

  val machineTypeStrMap: Map[String, MachineType] = Map("G" -> GMachine, "M" -> MMachine, "R" -> RMachine, "P" -> PMachine)

  def strToMachineType: String => MachineType = machineTypeStrMap(_)

  def parseOrderDesc: Int => String => Order = orderId => str => {
    Order(orderId, str.split(":").last.trim.split(" ").toList)
  }

  def naiveCreatePlan: List[ResourceDesc] => Order => Plan = rDesc => order => {
    val workProcesses = rDesc.flatMap(initializeMachine)
    naiveCreatePlanFromWorkProcess(workProcesses)(order)
  }

  def naiveCreatePlanFromWorkProcess: List[WorkProcess] => Order => Plan = workProcesses => order => {
    Plan(order.id, arrange(workProcesses)(order.products))
  }

  def arrange: List[WorkProcess] => List[Product] => List[WorkProcess] = workProcesses => products => {
    products.foldLeft(workProcesses)((wps, product) => arrangeProduction(wps)(product))
  }

  def arrangeProduction: List[WorkProcess] => Product => List[WorkProcess] = workProcesses => product => {
    workProcesses.groupBy(_.machine.mType).values.map(
      workProcessByType => assignProduct(workProcessByType, product)).flatten.toList.sortBy(_.machine.toString)
  }

  def assignProduct(processes: List[WorkProcess], product: Product): List[WorkProcess] = {
    val sorted = processes.sortBy(_.products.size)
    val idlest: WorkProcess = sorted(0)
    sorted.updated(0, WorkProcess(idlest.machine, idlest.products :+ product))
  }

  def initializeMachine: ResourceDesc => List[WorkProcess] = {
    case (machineType, quantity) => (1 to quantity).map(i => WorkProcess(Machine(machineType, i), Nil)).toList
  }

  def formatShowPlan: Plan => String = plan => {
    s"Order #${plan.orderId}\n" +
      plan.workProcess.map(w => s"${machine2Str(w.machine)}: ${w.products.mkString("\t")}").mkString("\n") + "\n"
      s"Total: ${calcTime(productMachineTime)(plan)} hours"
  }

  def machine2Str: Machine => String = m => s"${m.mType.toString.head}${m.id}"

  type Hour = Int
  type ProductMachineTime = Map[Product,Map[MachineType,Hour]]
  val productMachineTime: ProductMachineTime  = Map(
    "zteT" -> Map(GMachine -> 3,MMachine -> 3,RMachine -> 1,Cooler -> 2,PMachine -> 1),
    "zteW" -> Map(GMachine -> 2,MMachine -> 2,RMachine -> 3,Cooler -> 2,PMachine -> 2),
    "zteX" -> Map(GMachine -> 3,MMachine -> 3,RMachine -> 1,Cooler -> 5,PMachine -> 1),
    "zteY" -> Map(GMachine -> 1,MMachine -> 1,RMachine -> 4,Cooler -> 3,PMachine -> 3),
    "zteZ" -> Map(GMachine -> 2,MMachine -> 3,RMachine -> 2,Cooler -> 1,PMachine -> 1)
  )

  def calcTime:ProductMachineTime => Plan => Int = pmTime => plan => {
    (for(w <- plan.workProcess; p <- w.products) yield pmTime(p)(w.machine.mType)).sum
  }
}
