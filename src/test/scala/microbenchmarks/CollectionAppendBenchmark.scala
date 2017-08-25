package microbenchmarks

import org.scalameter.api._

object CollectionAppendBenchmark extends PerformanceTest.OfflineReport {
//  val sizes: Gen[Int] = Gen.range("size")(10000, 50000, 10000)
//
//  var list = List.empty[Int]
//  var array = ArrayBuffer.empty[Int]
//  var vector = Vector.empty[Int]
//
//  performance of "Vector" in {
//    measure method ":+" in {
//      using(sizes) in { s =>
//        var max = 0
//        while(max < s) {
//          vector = vector :+ 1
//          max += 1
//        }
//      }
//    }
//  }
//
//  performance of "List" in {
//    //can't
////    measure method ":+" in {
////      using(sizes) in { s =>
////        var max = 0
////        while(max < s) {
////          list = list :+ 1
////          max += 1
////        }
////      }
////    }
//    measure method ":: then reverse" in {
//      using(sizes) in { s =>
//        var max = 0
//        while(max < s) {
//          list = 1 :: list
//          max += 1
//        }
//        list.reverse
//      }
//    }
//  }
//
//  performance of "ArrayBuffer" in {
//    measure method "+=" in {
//      using(sizes) in { s =>
//        var max = 0
//        while(max < s) {
//          array += 1
//          max += 1
//        }
//      }
//    }
//  }
}
