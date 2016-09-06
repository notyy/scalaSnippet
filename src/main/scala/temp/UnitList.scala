package temp

object UnitList extends App{
  var list = List(())
  (1 to 10).foreach{ _ =>
    list = (1,2,3):: list
  }
  println(list)
}
