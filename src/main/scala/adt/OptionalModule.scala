package adt

object OptionalModule {

  sealed trait Optional[+T]

  case class Just[T](value: T) extends Optional[T]

  case object NotExist extends Optional[Nothing]

}
