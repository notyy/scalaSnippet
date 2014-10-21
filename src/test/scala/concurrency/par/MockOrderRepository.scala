package concurrency.par

trait MockOrderRepository extends OrderRepository{
  def orderData: Set[(CustomerId, OrderAmount)]

  override def filterByAmount(f: OrderAmount => Boolean): Set[(CustomerId, OrderAmount)] = {
    orderData.filter { case (_, amount) => f(amount)}
  }
}
