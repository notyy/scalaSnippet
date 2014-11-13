package fibs

object FibsModule {

  //  def fibs(length: Int): List[Int] =
  //    length match {
  //      case 1 => 1 :: Nil
  //      case 2 => 1 :: 1 :: Nil
  //      case n => {
  //        val prev = fibs(n - 1)
  //        prev match {
  //          case (x :: y :: _) => (x + y) :: prev
  //        }
  //      }
  //    }
  //
  //  def fibsMk2(length: Int) = fibs(length).reverse

  //  def fibs(length: Int): List[Int] =
  //    length match {
  //      case 1 => 1 :: Nil
  //      case 2 => 1 :: 1 :: Nil
  //      case n => {
  //        val prev = fibs(n - 1)
  //        (prev.head + fibs(n - 2).head) :: prev
  //      }
  //    }

  //  def fibs(length: Int): List[Int] = length match {
  //    case 1 => List(1)
  //    case 2 => List(1,1)
  //    case n => {
  //      val prev = fibs(n - 1)
  //      prev :+ (prev.last + fibs(n-2).last)
  //    }
  //  }

//  def fibs(length: Int): List[Int] = length match {
//    case 1 => List(1)
//    case 2 => List(1, 1)
//    case n => fibs(n - 1) :+ (fibs(n - 1).last + fibs(n - 2).last)
//  }

//  val fibs: Stream[Int] = 1 #:: 1 #:: (fibs zip fibs.tail).map { t => t._1 + t._2}

}
