package loanPattern

import java.io.{Reader, FileReader, BufferedReader}

import scala.io.Source

object SampleTxtProcessor {
  def countLines(fileName: String): Int = {
    //purposely use java style file processing instead of scala.io.Source.fromFile
    var reader: BufferedReader = null
    try {
      reader = new BufferedReader(new FileReader(fileName))
      var count = 0
      while (reader.readLine() != null) {
        count += 1
      }
      count
    }finally{
      if(reader != null){
        reader.close()
      }
    }
  }
}
