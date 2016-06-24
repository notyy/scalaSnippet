package finagle.thrift

import com.twitter.finagle.Thrift
import com.twitter.util.Await

object BinaryServiceApp extends App {
  val service = Thrift.serveIface("localhost:8001", new BinaryServiceImpl)
  Await.ready(service)
}
