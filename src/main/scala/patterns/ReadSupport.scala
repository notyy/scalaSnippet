package patterns

import scala.io.Source

trait ReadSupport {
  def read(path:Path): String = Source.fromFile(path.value).mkString
}
