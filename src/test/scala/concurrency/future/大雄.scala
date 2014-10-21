package concurrency.future

import java.util.Date

import com.typesafe.scalalogging.slf4j.LazyLogging
import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

case class Money(amount: Double)

case class Shoe(brand: String)

case class BasketBall(id: Int)

object 大雄 extends App with LazyLogging {
  val start = new Date().getTime
  takeCloth()
  val money = takeMoneyFromBank(500)
  buyShoesFromHongkong(money)
  takeBasketBall()
  playBasketBall()
  val end = new Date().getTime
  logger.info(s"total duration: ${end - start}")

  def takeCloth(): Unit = logger.info("taking up adidas")

  def takeBasketBall(): Unit = {
    logger.info("taking basketBall")
    val basketBall = borrowFrom静香()
    logger.info(s"got basketBall $basketBall")
  }

  def borrowFrom静香(): BasketBall = {
    logger.info("borrowing basketBall from 静香")
    Thread.sleep(5000)
    val basketBall = BasketBall(1)
    logger.info(s"basketBall $basketBall successfully borrowed from XiaoHong")
    basketBall
  }

  def buyShoesFromHongkong(money: Money): Shoe = {
    logger.info("buying shoes from hongkong")
    Thread.sleep(2000)
    val shoes = Shoe("Asics")
    logger.info(s"shoes bought from hongkong: $shoes")
    shoes
  }

  def takeMoneyFromBank(amount: Double): Money = {
    logger.info("taking money from bank")
    Thread.sleep(2000)
    val money = Money(amount)
    logger.info(s"money taken from bank $money")
    money
  }

  def playBasketBall() = println("wahahaha!!!")

}


