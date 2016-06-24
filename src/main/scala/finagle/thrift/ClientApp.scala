package finagle.thrift

import com.twitter.finagle.Thrift
import com.twitter.util.{Await, Future}
import notyy.github.thrift.BinaryService

object ClientApp extends App {
  val client = Thrift.newIface[BinaryService[Future]]("localhost:8001")
  println("calling fetch blog")
  val rs = client.fetchBlob(2)
  rs.onSuccess(println)
  rs.onFailure(ex => ex.printStackTrace())
  Await.ready(rs)
}
