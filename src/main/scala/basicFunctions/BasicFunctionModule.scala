package basicFunctions

object BasicFunctionModule {
  def add1: Int => Int = _ + 1

  def multi2: Int => Int = _ * 2

  def int2String: Int => String = _.toString

  def add1ThenMulti2: Int => Int = x => multi2(add1(x))
}
