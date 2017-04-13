package concurrency.promise

import com.typesafe.scalalogging.slf4j.{LazyLogging, StrictLogging}

import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

case class Money(amount: Double)

case class Shoe(brand: String)

object Double11 extends App with StrictLogging {
  logger.info("演出开始")
  val p = Promise[Money]()
  val f = p.future

  val 大雄的剧本 = Future {
    logger.info("大雄去给小静打钱")
    Thread.sleep(2000)
    p.success(Money(500))
    logger.info("大雄出去玩了")
    Thread.sleep(2000)
  }

  val 小静的剧本 = Future {
    logger.info("小静在逛淘宝")
    Thread.sleep(3000)
    f.onSuccess {
      case money => buyShoesFromHongkong(money)
    }
    logger.info("小静给大雄做晚饭")
    Thread.sleep(2000)
  }

  大雄的剧本.onSuccess{
    case _ => logger.info("大雄回家了")
  }

  小静的剧本.onSuccess{
    case _ => logger.info("小静和大雄高兴的一起吃饭")
  }

  Thread.sleep(6000)
  logger.info("演出结束")

  def buyShoesFromHongkong(money: Money): Shoe = {
    logger.info("buying shoes from hongkong")
    val shoes = Shoe("Asics")
    logger.info(s"shoes bought from hongkong: $shoes")
    shoes
  }
}
