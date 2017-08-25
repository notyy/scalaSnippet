package microbenchmarks

import org.scalameter.api._

object PerfOfWhile extends PerformanceTest.OfflineReport  {
  def whileLoop(size: Int): Int = {
    var c = 0
    while(c < size){
      {
        if ( c % 19 == 0) c+=3 else c+=1
      }
    }
    c
  }

  def foreachLoop(size: Int): Int = {
    var c = 0
    (1 to size).foreach(_ => c += 1)
    (1 to 10).indexOf(1)
    c
  }

  val sizes: Gen[Int] = Gen.range("size")(10000000, 100000000, 10000000)

  performance of "whileLoop" in {
    measure method "while" in {
      using(sizes) in { whileLoop }
    }
  }

  performance of "foreachLoop" in {
    measure method "foreach" in {
      using(sizes) in { foreachLoop }
    }
  }
}
