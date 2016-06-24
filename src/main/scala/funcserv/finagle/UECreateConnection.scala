package funcserv.finagle

import com.twitter.finagle.{Httpx, httpx, Service}
import com.twitter.util.{Await, Future}

object UECreateConnection extends App {
  val service = new Service[httpx.Request, httpx.Response] {
    def apply(req: httpx.Request): Future[httpx.Response] =
      Future.value {
        println(s"get req $req")
        httpx.Response(req.version, httpx.Status.Ok)
      }
  }

  val server = Httpx.serve(":8080", service)
  Await.ready(server)
}
