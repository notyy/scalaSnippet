package finagle

import com.twitter.finagle.{Httpx, Service, httpx}
import com.twitter.util.{Await, Future}

object ClientSample extends App {
  val client: Service[httpx.Request, httpx.Response] = Httpx.newService("127.0.0.1:8080")
  val request = httpx.Request(httpx.Method.Get, "/")
  request.host = "127.0.0.1"
  request.contentString = "test"
  val response: Future[httpx.Response] = client(request)
  println("Request has been sent")
  response.onSuccess { resp: httpx.Response =>
    println("GET success: " + resp)
  }
  Await.ready(response) //this doesn't work
  Thread.sleep(1000)
}
