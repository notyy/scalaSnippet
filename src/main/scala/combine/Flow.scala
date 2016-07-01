package combine

trait Flow[A,B]
case class LastStep[A,B](f: A => B) extends Flow[A,B]
case class CombinedSteps[A,B,C](head: A => B, tail: Flow[B,C])