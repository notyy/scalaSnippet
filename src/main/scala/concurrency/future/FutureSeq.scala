package concurrency.future

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object FutureSeq extends App {
  def calcX(x:Int): Future[Int] = Future.successful(x + 2)
  def calcY(x:Int): Future[Int] = Future.successful(x * 2)

  val rs = Await.result(Future.sequence(List(calcX(2),calcY(2))), 1 seconds)
  println(rs(0))
  println(rs(1))
}
