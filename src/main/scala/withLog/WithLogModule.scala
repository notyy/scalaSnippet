package withLog

object WithLogModule extends App{
  def withLog[A,B](funcName: String)(f: A => B): A => B = { x =>
    println(s"calling $funcName with $x")
    val rs = f(x)
    println(s"result is $rs")
    rs
  }

  def add1:Int => Int = withLog("add1"){x:Int => x + 1}

  add1(1)
}
