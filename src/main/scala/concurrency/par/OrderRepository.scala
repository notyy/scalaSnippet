package concurrency.par

trait OrderRepository{
  def filterByAmount(f: OrderAmount => Boolean): Set[(CustomerId, OrderAmount)] = ???
}
