package sample

object Segment {

  trait Expression
  trait Oprand

  case class <(value: Oprand, variable: Oprand) extends Expression with Oprand {
    def <(right: Oprand) = new <(this, right)
  } 
  
  case class Variable(name: String) extends Oprand

  case class Value(value: Int) extends Oprand {
    def <(variable: Variable): (<) = new <(this, variable)
    def <(str: String):(<) = <(Variable(str))
  }

  implicit def value2Int(value: Value):Int = value.value
  implicit def int2Value(value: Int):Value = Value(value)
}
