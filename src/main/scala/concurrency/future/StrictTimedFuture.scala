//package concurrency.future
//
//import java.util.concurrent.TimeoutException
//import java.util.{Timer, TimerTask}
//
//import scala.concurrent.{ExecutionContext, Future, Promise}
//import scala.concurrent.ExecutionContext.Implicits.global
//
//object StrictTimedFuture {
//
//  implicit class KestrelCombinator[A](val a: A) extends AnyVal {
//    def withSideEffect(fun: A => Unit): A = {
//      fun(a)
//      a
//    }
//
//    def tap(fun: A => Unit): A = withSideEffect(fun)
//  }
//
//  val timer = new Timer()
//
//  def apply[T](after: Long)(body: => T, onTimeout: => Unit = Unit)(implicit ec: ExecutionContext): Future[T] = {
//    val promise = Promise[T]()
//    val timeOutTask = new TimerTask {
//      def run() {
//        onTimeout
//        promise.failure(
//          new TimeoutException(s"Future timed out after ${after}ms in thread ${Thread.currentThread().getName}"))
//        println("interrupting")
//        Thread.currentThread().stop()
//      }
//    }
//    timer.schedule(timeOutTask, after)
//    // does not cancel future, only resolves result in approx duration. Your future may still be running!
//    Future.firstCompletedOf(Seq(Future(body), promise.future)).tap(_.onComplete { case result => timeOutTask.cancel() })
//  }
//}
//
//object StrictTimedFutureSample extends App {
//  println(s"main program running in ${Thread.currentThread().getName}")
//  val future = StrictTimedFuture(2000) {
//    var i = 1
//    while (true) {
//      Thread.sleep(1000)
//      println(s"run for $i time in thread ${Thread.currentThread().getName}")
//      i += 1
//    }
//    i
//  }
//  future.onSuccess { case i => s"get value $i" }
//  future.onFailure { case t => println(s"future run error, logged, ${t.getMessage}") }
//}