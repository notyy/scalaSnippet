package typeParameter

import scala.reflect.ClassTag

object InstanceTypeCheck extends App{
  def isInstanceOf[T: ClassTag](x: Any): Boolean = x match {
    case (x: T) => true
    case _ => false
  }

  println(isInstanceOf[Int]("x"))
  println(isInstanceOf[Int](1:Int))
  println(isInstanceOf[List[Int]](List("x")))
}
