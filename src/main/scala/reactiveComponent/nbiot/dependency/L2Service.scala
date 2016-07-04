package reactiveComponent.nbiot.dependency

import com.typesafe.scalalogging.slf4j.StrictLogging
import reactiveComponent.nbiot.flow.RRCRejectL2Notify
import reactiveComponent.nbiot.flow.RRCRejectL2Notify.{L2Timeout, RRCConnReject}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object L2Service extends StrictLogging {
  def rejectNotify: (RRCConnReject => Future[RRCRejectL2Notify.L2Timeout]) = rrcReject => {
    logger.info(s"sending reject notification to L2: $rrcReject")
    Thread.sleep(300)
    Future {
      L2Timeout()
    }
  }
}
