package loanPattern

trait SampleTxtProcessorLoanImpl {
  this: FileReadSupport =>

  def countLines(fileName: String): Int = withReader(fileName){ reader =>
    var count = 0
    while (reader.readLine() != null) {
      count += 1
    }
    count
  }

  def countFunctions(fileName: String): Int = withReader(fileName){ reader =>
    var count = 0
    var line = reader.readLine()
    while (line != null && line.startsWith("def")) {
      count += 1
      line = reader.readLine()
    }
    count
  }
}

object SampleTxtProcessorLoanImpl extends SampleTxtProcessorLoanImpl with FileReadSupport
