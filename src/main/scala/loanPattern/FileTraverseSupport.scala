package loanPattern

import java.io.BufferedReader

trait FileTraverseSupport {
  def fold[A](reader: BufferedReader)(init: A)(f: (A, String) => A): A = {
    var line = reader.readLine()
    var acc = init
    while (line != null) {
      acc = f(acc, line)
      line = reader.readLine()
    }
    acc
  }
}
