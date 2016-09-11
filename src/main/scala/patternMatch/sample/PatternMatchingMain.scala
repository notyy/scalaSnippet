package patternMatch.sample

object PatternMatchingMain extends App {
  val x: String = "2"
  x match {
    case "1" => println("its 1")
    case _ => println("don'nt know")
  }

  case class User(name: String, email: String, address: Address)

  case class Address(lane: String, no: String)

  val damotou = User("damotou", "xx@xx.xx", Address("xx lane", "xx no"))
  damotou match {
    case User(_, _, addr@Address(_, no)) => println(s"address is $addr no is $no")
  }

  val list = List(1, 2, 3)

  list match {
    case List(1, _, n) => println(n)
    case _ => println("no match")
  }

  val pair = (2, 2, 3)

  pair match {
    case (1, _, n) => println(n)
    case _ => println("no match")
  }

  val n: Any = 1
  n match {
    case x: Int if x > 1 => println("its a big int")
    case x: Int => println("its a small int")
    case x: String => println("its a string")
  }

  val (i, j) = (1, 2)
  println(i)
  println(j)

  val map = Map(1 -> "a", 2 -> "b")
  map.map { case (k, v) => s"key = $k, value = $v" }.foreach(println)
}
