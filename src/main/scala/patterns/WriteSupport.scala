package patterns

import java.io.{BufferedWriter, File, FileWriter}

trait WriteSupport {
  def withWriter[A](path: Path)(f: BufferedWriter => A): A = {
    var writer: BufferedWriter = null
    try {
      val file = new File(path.value)
      writer = new BufferedWriter(new FileWriter(file))
      f(writer)
    } finally {
      writer.close()
    }
  }
}
