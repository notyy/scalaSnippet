package concurrency.akka.simpleIO

import akka.actor.SupervisorStrategy.{Escalate, Restart, Resume, Stop}
import akka.actor.{Actor, ActorRef, OneForOneStrategy, Props}
import com.typesafe.scalalogging.slf4j.StrictLogging
import concurrency.akka.simpleIO.GreetingActor.{GreetResp, GreetingReq}
import concurrency.akka.simpleIO.MultiplyActor.NumberInput
import scala.concurrent.duration._

object MultiplyActor {
  def props(): Props = Props(new MultiplyActor)

  case class NumberInput(num: Int)
}

class MultiplyActor extends Actor with StrictLogging {

  override val supervisorStrategy =
    OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 minute) {
      case _: ArithmeticException      => Resume
      case _: NullPointerException     => Restart
      case _: IllegalArgumentException => Stop
      case _: Exception                => Escalate
    }

  val greetingActor: ActorRef = context.actorOf(GreetingActor.props())
//  var asker: Option[ActorRef] = None

  override def receive: Receive = {
    case NumberInput(i) => {
//      asker = Some(sender())
      val v = i * 2
      greetingActor ! GreetingReq(v.toString)
    }
    case GreetResp(v) => {
//      asker.foreach(_ ! v)
      logger.info(v)
    }
  }
}
