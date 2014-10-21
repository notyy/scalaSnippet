package concurrency.par

import com.typesafe.scalalogging.slf4j.LazyLogging

trait CustomerRepository extends LazyLogging {

  def fetch(cid: CustomerId): Option[Name] = ???
}
