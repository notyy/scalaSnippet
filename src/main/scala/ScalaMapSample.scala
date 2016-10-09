object ScalaMapSample extends App {
  def process(xs: List[Int]): List[Int] = {
    xs.par.filter(x => {
      println(s"filtering $x in thread ${Thread.currentThread().getName}")
      x > 2
    }).map(x => {
      println(s"mapping $x in thread ${Thread.currentThread().getName}")
      x * 2
    }).toList
  }

  process(List(1,2,3,4,5))
}



//    xs.par.filter(_ > 2).map(_ * 2).toList
