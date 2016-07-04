package reactiveComponent.nbiot.flow

import reactiveComponent.framework.SimpleTransformation

import scala.concurrent.Future

class RRCConnectionReject extends SimpleTransformation[UeIdentity, Boolean]{
  override def update(input: UeIdentity): Future[Boolean] = ???
}
