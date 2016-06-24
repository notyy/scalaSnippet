package fold

object sum extends App {
  def sum(xs:List[Int]):Int = {
    var rs = 0
    for(i <- xs) rs += i
    rs
  }

  def product(xs:List[Int]):Int = {
    var rs = 1
    for(i <- xs) rs = rs * i
    rs
  }

  println(s"sum = ${sum(List(3,4,5))}")
  println(s"product = ${product(List(3,4,5))}")
}
