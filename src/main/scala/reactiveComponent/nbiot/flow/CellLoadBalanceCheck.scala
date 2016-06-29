package reactiveComponent.nbiot.flow

import reactiveComponent.framework.StatefulComponent
import reactiveComponent.nbiot.flow.CellLoadBalanceCheck.{CheckResult, Model}
import reactiveComponent.nbiot.source.UL_CCCH_MSG

import scala.concurrent.Future

class CellLoadBalanceCheck extends StatefulComponent[UL_CCCH_MSG, Model, CheckResult] {
  override def init: Model = Model(Map.empty[CellId, Int])

  override def update(input: UL_CCCH_MSG, model: Model): (Model, Option[Future[UL_CCCH_MSG]], CheckResult) = {
    val newCount = model.cellRRCCount.getOrElse(input.cellId, 0)
    if (newCount > CellLoadBalanceCheck.MAX_RCC_PER_CELL) {
      (model, None, CheckResult(input, allow = false))
    } else {
      (Model(model.cellRRCCount.updated(input.cellId, newCount + 1)), None, CheckResult(input, allow = true))
    }
  }
}

object CellLoadBalanceCheck {
  val MAX_RCC_PER_CELL = 5

  case class CheckResult(ul_ccch_msg: UL_CCCH_MSG, allow: Boolean)

  case class Model(cellRRCCount: Map[CellId, Int])

}
