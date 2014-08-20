package patterns

import java.io.{FileWriter, BufferedWriter, File}

import scala.io.Source

case class Path(value: String) extends AnyVal

class FileTransformer extends WriteSupport with ReadSupport{
  def toUpperCase(path: Path): Unit = {
    val content = read(path)
    withWriter(path){ w =>
      w.write(content.toUpperCase)
    }
  }

  def toLowerCase(path: Path): Unit = {
    val content = read(path)
    withWriter(path){ w =>
      w.write(content.toLowerCase)
    }
  }

  //  def separate(path: Path, separator: String): Unit = ???
}
