package concurrency.future

import com.typesafe.scalalogging.slf4j.LazyLogging
import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global

case class Money(amount: Double)

case class Shoe(brand: String)

case class BasketBall(id: Int)

object 大熊 extends App with LazyLogging {

  takeCloth()
  takeBasketBall()
  takeShoes()
  playBasketBall()

  def takeCloth(): Unit = logger.info("taking up adidas")

  def takeShoes(): Unit = {
    logger.info("taking Shoes")
    val money = takeMoneyFromBank(500)
    val shoes = buyShoesFromHongkong(money)
    logger.info(s"got shoes $shoes")
  }

  def takeBasketBall(): Unit = {
    logger.info("taking basketBall")
    val basketBall = borrowFrom静香()
    logger.info(s"got basketBall $basketBall")
  }

  def borrowFrom静香(): BasketBall = {
    logger.info("borrowing basketBall from XiaoHong")
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
    Thread.sleep(1000)
    val money = Money(amount)
    logger.info(s"money taken from bank $money")
    money
  }

  def playBasketBall() = println("wahahaha!!!")

}


