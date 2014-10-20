package concurrency.par

trait OrderDataSelector{
  def orderData: Set[(CustomerId, OrderAmount)]

  def filterByAmount(f: OrderAmount => Boolean): Set[(CustomerId, OrderAmount)] = {
    orderData.filter { case (_, amount) => f(amount)}
  }
}
