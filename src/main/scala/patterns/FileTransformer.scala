package patterns

case class Path(value: String) extends AnyVal

trait FileTransformer {
  def save(path: Path, content: String): Unit = ???

  def load(path: Path): String = ???

  def toUpperCase(path: Path): Unit = ???

  def toLowerCase(path: Path): Unit = ???

  def separate(path: Path, separator: String): Unit = ???
}
