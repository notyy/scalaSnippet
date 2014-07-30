package monad

case class FilePath(value: String)

trait Persisted[A <: Any] {
  def path: FilePath
  def v: A

  def get: A

  def map[B <: Any](f: A => B): Persisted[B]
}

case class PersistedImpl[T](path: FilePath, v: T) extends Persisted[T] {
  override def map[B](f: (T) => B): Persisted[B] = Persisted(path, f(v))

  override def get: T = v
}

object Persisted extends FileWriteSupport {
  def apply[T <: Any](path: FilePath, v: T): Persisted[T] = {
    withWriter(path) { writer =>
      writer.write(v.toString)
    }
    PersistedImpl(path, v)
  }
}

