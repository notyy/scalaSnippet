package utils

import scala.io.Source

object StreamBenchmark extends App {
  val runtime: Runtime = Runtime.getRuntime
  println(s"max memory ${b2m(runtime.maxMemory())}MB")
  println(s"total memory ${b2m(runtime.totalMemory())}")
  println(s"init free memory ${b2m(runtime.freeMemory())}")
  val file = Source.fromFile("/Users/twer/source/bigdata/data/new2.dat")
  var size = 0
  file.getLines().toStream.foreach(_ => size += 1)
  //file.getLines().toList.foreach(_ => size += 1)
  //file.getLines().foreach(_ => size += 1)
  println(s"data size: $size")

  //println(s"data size: ${file.getLines().toList.size}")
  //println(s"data size: ${file.getLines().size}")
  //runtime.gc()
  println(s"remain total memory ${b2m(runtime.totalMemory())}")
  println(s"remain free memory ${b2m(runtime.freeMemory())}")


  def b2m(byte: Long): String = s"${byte / 1024 /1024}MB"
}
