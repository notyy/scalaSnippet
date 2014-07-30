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
}

object SampleTxtProcessorLoanImpl extends SampleTxtProcessorLoanImpl with FileReadSupport
