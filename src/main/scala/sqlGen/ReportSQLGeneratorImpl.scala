package sqlGen
import sqlGen.Domain.{SQL, TimeSegment}

trait ReportSQLGenerator {
  def genSQL(timeSegments: List[TimeSegment]): SQL
}

trait ReportSQLGeneratorImpl extends ReportSQLGenerator {
  this: GranularitySQLGenerator with TimeSegmentSQLGenerator with DBConfigLoader =>

  override def genSQL(timeSegments: List[TimeSegment]): SQL = {
    SQL(s"SELECT * FROM $calls WHERE granularity = ${genGranularitySQL.str}" +
      s" AND ${genTimeSegmentsSQL(timeSegments).str} AND configInDB = ${loadConfig.str}")
  }

  def calls = "calls"
}
