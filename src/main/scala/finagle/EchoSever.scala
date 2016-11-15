package finagle

import com.twitter.finagle.{Httpx, Service, httpx}
import com.twitter.util.{Await, Future}

object EchoSever extends App {
  val service = new Service[httpx.Request, httpx.Response] {
    def apply(req: httpx.Request): Future[httpx.Response] =
      Future.value {
        println(s"get req $req, content is ${req.contentString}")
        Thread.sleep(2000)
        httpx.Response(req.version, httpx.Status.Ok)
      }
  }

  val server = Httpx.serve(":8080", service)
  Await.ready(server)
}
