package concurrency.akka.timer

import akka.actor.{Actor, ActorSystem, PoisonPill, Props}
import com.typesafe.scalalogging.slf4j.StrictLogging

case object STOP
case object WORK

class SepTask extends Actor with StrictLogging {
  var working = true
  var count = 1

  override def receive: Receive = {
    case STOP => {
      logger.info("get STOP order")
      working = false
      logger.info("work stopped")
    }
    case WORK if working => {
      logger.info("get WORK order")
      logger.info(s"calc $count")
      Thread.sleep(1000)
      count += 1
      self ! WORK
      logger.info("work done")
    }
    case x => logger.info(s"ignore order $x")
  }

  //this proves that the following loop always occupy thread, 
  // that the STOP message doesn't have chance to be processed 
//  while(working){
//    logger.info(s"calc $count")
//    Thread.sleep(1000)
//    count += 1
//  }
}

object SepTaskSample extends App with StrictLogging {
  val system = ActorSystem("pingpang")
  val sepTask = system.actorOf(Props[SepTask], "task1")
  logger.info("start!")
  sepTask ! WORK
  Thread.sleep(3000)
  sepTask ! STOP
  Thread.sleep(3000)
  sepTask ! PoisonPill
  Thread.sleep(1000)
  system.terminate()
}
