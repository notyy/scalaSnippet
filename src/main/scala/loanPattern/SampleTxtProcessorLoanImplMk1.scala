package loanPattern

trait SampleTxtProcessorLoanImplMk1 {
  this: FileReadSupport with FileTraverseSupport =>

  def countLines(fileName: String): Int = countBy(fileName)(_ => true)

  def countFunctions(fileName: String): Int = countBy(fileName)(_.startsWith("def"))

  def countBy(fileName: String)(f: String => Boolean): Int = withReader(fileName){ reader =>
    fold(reader)(0){
      (count, line) => if(f(line)) count + 1 else count
    }
  }
}

object SampleTxtProcessorLoanImplMk1 extends SampleTxtProcessorLoanImplMk1 with FileReadSupport with FileTraverseSupport
