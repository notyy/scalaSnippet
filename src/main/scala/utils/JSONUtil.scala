package utils


import org.json4s._
import org.json4s.native.Serialization
import scala.util.Try

object JSONUtil {

  implicit val formats = DefaultFormats

  def toJSON(objectToWrite: AnyRef): String = Serialization.write(objectToWrite)

  def fromJSONOption[T](jsonString: String)(implicit mf: Manifest[T]): Option[T] = Try(Serialization.read(jsonString)).toOption

}
