package patterns

trait Persist[String] {
  def get: String

  def map(f: String => String): Persist[String]
}

object Persist {

  def apply(path: Path, content: String): Persist[String] = ???
}
