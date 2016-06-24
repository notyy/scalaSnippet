package collection

object collection extends App {
  //dup(List(1,2,3)) should be List(1,1,2,2,3,3)
//  def dup[A]: List[A] => List[A] = ???

  def dup[A]: List[A] => List[A] = _.map(x => List(x,x)).flatten

  println(dup(List(1,2,3)))
}
