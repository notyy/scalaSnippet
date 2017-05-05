package sqlGen

import org.scalatest.{FunSpec, Matchers}
import sqlGen.Domain._

class ReportSQLGeneratorImplTest extends FunSpec with Matchers {
  
  trait SampleDBConfigLoader extends DBConfigLoader{
    override def loadConfig: SQLPart = SQLPart("'dbproperties'")
  }
  
  describe("ReportSQLGenerator"){
    it("should generate sql for producing report"){
      val granularity:Granularity = RNCGranularity

      val reportGenerator = granularity match {
        case RNCGranularity => new ReportSQLGeneratorImpl with TimeSegmentSQLGenerator with RNCGranularitySQLGenerator with SampleDBConfigLoader {}
        case CELLGranularity => new ReportSQLGeneratorImpl with TimeSegmentSQLGenerator with CELLGranularitySQLGenerator with SampleDBConfigLoader {}
      }

      val sql = reportGenerator.genSQL(List(TimeSegment("2014-03-04", "2014-04-05")))
      sql shouldBe SQL("SELECT * FROM calls WHERE granularity = 'RNC' " +
        "AND (timeSegments < '2014-04-05' AND timeSegments > '2014-03-04') AND configInDB = 'dbproperties'")
    }
  }
}
