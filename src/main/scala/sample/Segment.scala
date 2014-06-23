package sample

object Segment {

  trait Expression

  case class <(value: Expression, variable: Expression) extends Expression {
    def <(right: Expression) = new <(this, right)
  } 
  
  case class Variable(name: String) extends Expression

  case class Value(value: Int) extends Expression {
    def <(variable: Variable): (<) = new <(this, variable)
    def <(str: String):(<) = <(Variable(str))
  }

  implicit def int2Value(value: Int):Value = Value(value)
}
