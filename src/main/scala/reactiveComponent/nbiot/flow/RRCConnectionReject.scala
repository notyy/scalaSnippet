package reactiveComponent.nbiot.flow

import reactiveComponent.framework.SimpleTransformation

import scala.concurrent.Future

class RRCConnectionReject extends SimpleTransformation[UeIdentity, Boolean]{
  override def doUpdate(input: UeIdentity): Future[Boolean] = ???

  override def processName: String = "RRCConnectionReject"
}
