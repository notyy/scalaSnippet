package sqlGen
import sqlGen.Domain.{Granularity, SQL, TimeSegment}

trait ReportSQLGenerator {
  this: GranularitySQLGenerator with TimeSegmentSQLGenerator with DBConfigLoader =>

  def genSQL(granularity: Granularity, timeSegments: List[TimeSegment]): SQL = {
    SQL(s"SELECT * FROM calls WHERE granularity = ${genGranularitySQL.str}" +
      s" AND ${genTimeSegmentsSQL(timeSegments).str} AND configInDB = ${loadConfig.str}")
  }
}
