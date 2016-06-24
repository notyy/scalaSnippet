package patternMatch.ifversion

object BinarySearchWithIf extends App {
  // demo from <<<functional programming in scala>>
  def binarySearch(ds: Array[Double], key: Double): Int = {
    @annotation.tailrec
    def go(low: Int, mid: Int, high: Int): Int = {
//      if (low > high) -mid - 1
//      else {
        val mid2 = (low + high) / 2
        val d = ds(mid2)
        if (d == key) mid2
        else if (d > key) go(low, mid2, mid2 - 1)
        else go(mid2 + 1, mid2, high)
//      }
    }
    go(0, 0, ds.length - 1)
  }

  def isSorted[A](as: Array[A])(gt: (A,A) => Boolean): Boolean={

    val leng=as.length

    def getItem(start:Int):Boolean={
      if(gt(as(start),as(start+1))){
        if(start<=leng-2)
          getItem(start+1)
        else
          true
      }else{
        false
      }
    }
    getItem(0)
  }

  val doubles: Array[Double] = Array(1.0, 2.0, 3.0, 4.0, 5.0)
  val doubles1: Array[Double] = Array(5.0,4.0,3.0,2.0,1.0)
  val idx = binarySearch(doubles, 3.0)
  println(s"index of 3.0 is $idx")

  println(s"sorted: ${isSorted(doubles)(_ > _)}")
  println(s"sorted: ${isSorted(doubles1)(_ > _)}")
}
