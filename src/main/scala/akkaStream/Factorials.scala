package akkaStream

import java.nio.file.Paths

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream._
import akka.stream.scaladsl._
import akka.util.ByteString

import scala.concurrent.Future


object Factorials extends App {

  implicit val system = ActorSystem("QuickStart")
  implicit val materializer = ActorMaterializer()

  val source: Source[Int, NotUsed] = Source(1 to 100)
  source.runForeach(println)

  val factorials = source.scan(BigInt(1))((acc, next) => acc * next)

  def lineSink(fileName: String): Sink[String, Future[IOResult]] = {
    Flow[String]
      .map(num => ByteString(s"$num\n"))
      .toMat(FileIO.toPath(Paths.get(fileName)))(Keep.right)
  }

  factorials.map(_.toString)
    .runWith(lineSink("temp/factorials2.txt"))
}
