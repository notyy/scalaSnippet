package sqlGen

object Domain {
  trait Granularity
  case object RNCGranularity extends Granularity
  case object CELLGranularity extends Granularity

  case class TimeSegment(start: String, end: String)

  case class SQL(str: String)
  case class SQLPart(str: String)
}
