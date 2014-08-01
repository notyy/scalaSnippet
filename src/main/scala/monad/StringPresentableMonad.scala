package monad

object StringPresentableMonad {

  trait StringPresentable[T] {
    def toString(value: T): String

    def fromString(value: String): T
  }

  implicit object stringIsStringPresentable extends StringPresentable[String] {
    override def toString(value: String): String = value.toString

    override def fromString(value: String): String = value
  }

//  class ListIsStringPresentable[T] extends StringPresentable[List[T]] {
//    def toStringImpli(value: List[T])(implicit m: StringPresentable[T]): String = value.map(m.toString).mkString("\n")
//
//    def fromStringImpli(value: String)(implicit m: StringPresentable[T]): List[T] = value.lines.toList.map(m.fromString)
//
//    override def toString(value: List[T]): String = toStringImpli(value)
//
//    override def fromString(value: String): List[T] = fromStringImpli(value)
//  }

  case class Account(owner: String, balance: Double)

  implicit object accountIsStringPresentable extends StringPresentable[Account]{
    override def toString(value: Account): String = s"${value.owner},${value.balance}"

    override def fromString(value: String): Account = {
      val values = value.split(",")
      Account(values(0), values(1).toDouble)
    }
  }
}
