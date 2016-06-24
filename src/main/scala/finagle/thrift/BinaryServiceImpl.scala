package finagle.thrift

import com.twitter.util.Future
import notyy.github.thrift.BinaryService

class BinaryServiceImpl extends BinaryService[Future] {
  override def fetchBlob(id: Long): Future[String] = Future.value {
    println(s"called from client with id $id")
    "hello,world"
  }
}
