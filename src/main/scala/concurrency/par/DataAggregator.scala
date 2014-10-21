package concurrency.par

trait DataAggregator {
  this: CustomerRepository with OrderRepository =>

  def aggregate(f: OrderAmount => Boolean): Set[(Name, OrderAmount)] = {
    filterByAmount(f).par.map { case (cid, oAmount) => (fetch(cid).get, oAmount)}.seq
  }
}

object DataAggregator extends CustomerRepository with OrderRepository

