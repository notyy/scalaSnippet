package reactiveComponent.nbiot.flow

import com.typesafe.scalalogging.StrictLogging
import reactiveComponent.Platform
import reactiveComponent.framework.{KeyedInput, StatefulComponent}
import reactiveComponent.nbiot.flow.CellLoadBalanceCheck.CellUECount
import reactiveComponent.nbiot.flow.RRCProcessing._
import reactiveComponent.nbiot.source.UL_CCCH_MSG
import utils.JSONUtil

import scala.concurrent.Future
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

class RRCProcessing extends StatefulComponent[Input, Option[RRCInstance], RRCInstance] with StrictLogging {

  override def processName: String = "RRCProcess"

  override def modelSerialize(model: RRCInstance): String = {
    logger.info(s"serialize model $model")
    val json = JSONUtil.toJSON(model)
    logger.info(s"json for model $model is $json")
    json
  }


  override def extract(model: String): RRCInstance = {
    logger.info(s"extract model from json $model")
    val rsModel = JSONUtil.fromJSONOption[RRCInstance](model).get
    logger.info(s"model is $rsModel")
    rsModel
  }

  override def doUpdate(model: Option[RRCInstance], input: Input): Future[(Option[RRCInstance], Option[RRCInstance])] = {
    input match {
      case RRCConnReq(ulccchmsg) => {
        logger.info("processing RRCConnReq")
        Platform.run(new RRCConnectionSetup)(ulccchmsg, CellUECount(3)).map {
          case Success(rRCInstance) => (Some(rRCInstance), Some(rRCInstance))
          case Failure(e) => {
            e.printStackTrace()
            (None, None)
          }
        }
      }
      case NasTransport(ueId, cellId, content) => {
        logger.info("processing NasTransport")
        Future{ (model, model) }
      }
      case RRCRelease(ueId, cellId) => {
        logger.info("processing RRCRelease")
        Future(None,None)
      }
    }
  }
}

object RRCProcessing {

  //Model
  case class RRCInstance(ueId: UeId, rrcConnReason: String, cellId: CellId, coverRate: Double)

  //Input
  trait Input extends KeyedInput

  case class RRCConnReq(uL_CCCH_MSG: UL_CCCH_MSG) extends Input {
    override def key: String = s"s{ueId: ${uL_CCCH_MSG.ueId}, cellId: ${uL_CCCH_MSG.cellId}"
  }

  case class NasTransport(ueId: UeId, cellId: CellId, content: String) extends Input {
    override def key: String = s"s{ueId: $ueId, cellId: $cellId"
  }

  case class RRCRelease(ueId: UeId, cellId: CellId) extends Input {
    override def key: String = s"s{ueId: $ueId, cellId: $cellId"
  }

}
