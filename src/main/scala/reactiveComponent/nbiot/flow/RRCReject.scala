package reactiveComponent.nbiot.flow

import reactiveComponent.framework.StatefulComponent
import reactiveComponent.nbiot.flow.RRCReject.{Input, Model}
import reactiveComponent.nbiot.source.UL_CCCH_MSG

import scala.concurrent.Future

class RRCReject extends StatefulComponent[Model, Input, Unit]{
  override def update(model: Model, A: Input): (Model, Option[Future[Input]], Option[Unit]) = ???
}

object RRCReject {
  trait Input
  case class L2Input(uL_CCCH_MSG: UL_CCCH_MSG) extends Input
  case object Timeout extends Input

  case class Model()
}
