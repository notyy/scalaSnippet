package concurrency.par

import com.typesafe.scalalogging.LazyLogging

trait CustomerRepository extends LazyLogging {

  def fetch(cid: CustomerId): Option[Name] = ???
}
