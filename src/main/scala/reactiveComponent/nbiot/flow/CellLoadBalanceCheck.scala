package reactiveComponent.nbiot.flow

import reactiveComponent.framework.SimpleTransformation
import reactiveComponent.nbiot.flow.CellLoadBalanceCheck.{CellUECount, CheckResult}
import reactiveComponent.nbiot.source.UL_CCCH_MSG

class CellLoadBalanceCheck extends SimpleTransformation[(UL_CCCH_MSG, CellUECount), CheckResult] {

  override def update(input: (UL_CCCH_MSG, CellUECount)): CheckResult = input match {
    case (msg, CellUECount(count)) if count > CellLoadBalanceCheck.MAX_RCC_PER_CELL => CheckResult(msg, allow = false)
    case (msg, _) => CheckResult(msg, allow = true)
  }
}


object CellLoadBalanceCheck {
  val MAX_RCC_PER_CELL = 5

  case class CheckResult(ul_ccch_msg: UL_CCCH_MSG, allow: Boolean)

  case class CellUECount(value: Int) extends AnyVal

  case class Output()

}
