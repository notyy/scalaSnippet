package concurrency.par

import com.typesafe.scalalogging.slf4j.LazyLogging

trait CustomerDataProvider extends LazyLogging {
  def customerData: Set[(CustomerId, Name)]

  def fetch(cid: CustomerId): Option[Name] = {
    logger.info(s"getting customer name for cid:$cid")
    Thread.sleep(1000)
    val rs = customerData.find(_._1 == cid).map(_._2)
    logger.info(s"customer name for cid:$cid is $rs")
    rs
  }
}
