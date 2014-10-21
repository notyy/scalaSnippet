package concurrency.par

trait MockCustomerRepository extends CustomerRepository{
  def customerData: Set[(CustomerId, Name)]

  override def fetch(cid: CustomerId): Option[Name] = {
    logger.info(s"getting customer name for cid:$cid")
    Thread.sleep(1000)
    val rs = customerData.find(_._1 == cid).map(_._2)
    logger.info(s"customer name for cid:$cid is $rs")
    rs
  }
}
