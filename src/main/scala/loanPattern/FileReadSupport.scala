package loanPattern

import java.io.{FileReader, BufferedReader}

trait FileReadSupport {
  def withReader[T](fileName: String)(f: BufferedReader => T): T = {
    //purposely use java style file processing instead of scala.io.Source.fromFile
    var reader: BufferedReader = null
    try {
      reader = new BufferedReader(new FileReader(fileName))
      f(reader)
    }finally{
      if(reader != null){
        reader.close()
      }
    }
  }

  withReader("/tmp/test.txt"){ bf =>
    val fstLine = bf.readLine()
    println(fstLine)
  }

}
