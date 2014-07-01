package sqlGen

import org.scalatest.{ShouldMatchers, FunSpec}
import sqlGen.Domain._

class ReportSQLGeneratorTest extends FunSpec with ShouldMatchers {
  describe("ReportSQLGenerator"){
    it("should generate sql for producing report"){
      val granularity:Granularity = RNCGranularity

      val reportGenerator = granularity match {
        case RNCGranularity => new ReportSQLGenerator with TimeSegmentSQLGenerator with RNCGranularitySQLGenerator with DBConfigLoader {}
        case CELLGranularity => new ReportSQLGenerator with TimeSegmentSQLGenerator with CELLGranularitySQLGenerator with DBConfigLoader {}
      }

      val sql = reportGenerator.genSQL(RNCGranularity, List(TimeSegment("2014-03-04", "2014-04-05")))
      sql shouldBe SQL("SELECT * FROM calls WHERE granularity = 'RNC' " +
        "AND (timeSegments < '2014-04-05' AND timeSegments > '2014-03-04') AND configInDB = 'dbproperties'")
    }
  }
}
