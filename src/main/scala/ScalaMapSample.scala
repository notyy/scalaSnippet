object ScalaMapSample {
  def process(xs: List[Int]): List[Int] = {
    xs.filter(_ > 2).map(_ * 2)

//    xs.par.filter(x => {
//      println(s"filtering $x in thread ${Thread.currentThread().getName}")
//      x > 2
//    }).map(x => {
//      println(s"mapping $x in thread ${Thread.currentThread().getName}")
//      x * 2
//    }).toList
  }
}
