package dci

trait TransferSource {
  this: Account =>

  def transferOut(money: Double) = balance -= money
}

trait TransferTarget {
  this: Account =>

  def transferIn(money: Double) = balance += money
}

object TransferService {
  def transfer(source: TransferSource, target: TransferTarget, money: Double) = {
    source.transferOut(money)
    target.transferIn(money)
  }
}

object TransferApp extends App{
  val source = new Account("xx", 100) with TransferSource
  val target = new Account("yy", 100) with TransferTarget

  TransferService.transfer(source,target, 50)
}
