package concurrency.akkaStream

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source
import com.typesafe.scalalogging.StrictLogging
import concurrency.akkaStream.MapUtil._

object WordCount extends App with StrictLogging {
  implicit val system = ActorSystem("WordCount")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher

  val path = args(0)

  val source = Source.fromIterator(() => scala.io.Source.fromFile(path).getLines())
  val flow = source.map(_.split(" ").foldLeft(Map[String, Int]()) { (wordCountMap, word) =>
    if (wordCountMap.contains(word)) {
      wordCountMap.updated(word, wordCountMap(word) + 1)
    } else {
      wordCountMap.updated(word, 1)
    }
  })
    .reduce((wordCountMap1, wordCountMap2) => mergeMaps(wordCountMap1, wordCountMap2)(_ + _))

  logger.info("flow is ready to run")
  val result = flow.runForeach(println)
  result.onComplete {
    rs =>
      println(s"complete: $rs")
      system.terminate()
  }
}
