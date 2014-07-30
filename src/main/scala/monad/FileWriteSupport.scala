package monad

import java.io.{FileWriter, File, BufferedWriter}

trait FileWriteSupport {
  def withWriter(path: FilePath)(f: BufferedWriter => Unit): Unit = {
    var writer: BufferedWriter = null
    try {
      val file = new File(path.value)
      if (!file.exists() || file.isDirectory) {
        file.createNewFile()
      }
      writer = new BufferedWriter(new FileWriter(file))
      f(writer)
    } finally {
      if(writer != null) writer.close()
    }
  }
}
