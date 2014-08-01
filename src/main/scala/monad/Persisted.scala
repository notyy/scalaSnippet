package monad

import monad.StringPresentable.StringPresentable

import scala.io.Source

case class FilePath(value: String) extends AnyVal

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

  private def write(path: FilePath, value : String) = {
    withWriter(path) { writer =>
      writer.write(value)
    }
  }

  def read[T](path: FilePath)(implicit m: StringPresentable[T]): T = {
    m.fromString(Source.fromFile(path.value).getLines().mkString("\n"))
  }

  def readList[T](path: FilePath)(implicit m: StringPresentable[T]): List[T] = {
    Source.fromFile(path.value).getLines().map(m.fromString).toList
  }

  def apply[T](path: FilePath, v: T)(implicit m: StringPresentable[T]): Persisted[T] = {
    write(path, m.toString(v))
    PersistedImpl(path, v)
  }

  def apply[T](path: FilePath, v: List[T])(implicit m: StringPresentable[T]): Persisted[List[T]] = {
    write(path, v.map(m.toString).mkString("\n"))

    val pathTemp = path
    new Persisted[List[T]] {
      override def get: List[T] = v

      override def path: FilePath = pathTemp

      override def map[B](f: (List[T]) => B)(implicit m: StringPresentable[B]): Persisted[B] = Persisted(path, f(v))
    }
  }
}

