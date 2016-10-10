package adt

object OptionalModule {

  sealed trait Optional[+T]{
    def get: T
    def isEmpty: Boolean
    def getOrElse[B >: T](default: B):B
  }

  case class Just[T](value: T) extends Optional[T] {
    override def get: T = value

    override def isEmpty: Boolean = false

    override def getOrElse[B >: T](default: B): B = value
  }

  case object NotExist extends Optional[Nothing] {
    override def get: Nothing = ???

    override def isEmpty: Boolean = true

    override def getOrElse[B >: Nothing](default: B): B = default
  }

}
