package concurrency.par

trait DataAggregator {
  this: CustomerDataProvider with OrderDataSelector =>

  def aggregate(f: OrderAmount => Boolean): Set[(Name, OrderAmount)] = {
    filterByAmount(f).par.map { case (cid, oAmount) => (fetch(cid).get, oAmount)}.seq
  }
}
