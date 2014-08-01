package monad

import monad.StringPresentableMonad.StringPresentable

import scala.io.Source

case class FilePath(value: String)

trait Persisted[A] {
  def path: FilePath

  def get: A

  def map[B](f: A => B)(implicit m: StringPresentable[B]): Persisted[B]
}

case class PersistedImpl[A](path: FilePath, v: A)(implicit m: StringPresentable[A]) extends Persisted[A] {
  override def map[B](f: (A) => B)(implicit m: StringPresentable[B]): Persisted[B] = Persisted(path, f(v))

  override def get: A = v
}

object Persisted extends FileWriteSupport {
  def read[T](path: FilePath)(implicit m: StringPresentable[T]): T = {
    m.fromString(Source.fromFile(path.value).getLines().mkString("\n"))
  }

  def apply[T](path: FilePath, v: T)(implicit m: StringPresentable[T]): Persisted[T] = {
    withWriter(path) { writer =>
      writer.write(m.toString(v))
    }
    PersistedImpl(path, v)
  }
}

