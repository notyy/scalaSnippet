package reactiveComponent.nbiot.flow

import reactiveComponent.framework.{Result, StatefulComponent}
import reactiveComponent.nbiot.flow.RBSetup.Model

class RBSetup extends StatefulComponent[Model, RBSetup.Input, Boolean]{
  override def update(model: Model, input: RBSetup.Input): (Model, Result[RBSetup.Input, Boolean]) = ???
}

object RBSetup {
  case class Model(ueId: UeId, cellId: CellId)

  trait Input
  case class RBSetupReq(mac: String) extends Input
  case class Success()
  case class Failure(reason: String)

}
