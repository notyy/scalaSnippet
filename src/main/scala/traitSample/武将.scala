package traitSample

trait 武将 {
  def 杀():Unit = ???
  def 闪():Unit = ???
}

trait 枪将{
  def 突刺(): Unit = ???
}

object 张飞 extends 武将 with 枪将

object 三国游戏 extends App {
  张飞.突刺()
}