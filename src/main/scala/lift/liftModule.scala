package lift

object liftModule extends App{
  def add(x:Int)(y:Int):Int = x + y

  def lift[A,B,C](f: A => B => C): Option[A] => Option[B] => Option[C] = {
    // o1 => o2 => o1.flatMap(x => o2.map(y => f(x)(y)))
    o1 => o2 => for{
      x <- o1
      y <- o2
    } yield f(x)(y)
  }

  val fadd = lift(add)

  add(1)(2)

  fadd(Some(1))(Some(2))
}
