package sqlGen

import org.scalatest.{FunSpec, ShouldMatchers}
import sqlGen.Domain.{SQLPart, TimeSegment}

class TimeSegmentSQLGeneratorTest extends FunSpec with ShouldMatchers {
  describe("genTimeSegmentsSQL") {
    it("should generate sql for time segment parameter") {
      val timeSegmentSQLGenerator = new TimeSegmentSQLGenerator {}
      val sql = timeSegmentSQLGenerator.genTimeSegmentsSQL(List(TimeSegment("2014-03-04", "2014-04-05")))
      sql shouldBe SQLPart("(timeSegments < '2014-04-05' AND timeSegments > '2014-03-04')")
    }
    it("should generator OR expression for multiple time segments") {
      val timeSegmentSQLGenerator = new TimeSegmentSQLGenerator {}
      val sql = timeSegmentSQLGenerator.genTimeSegmentsSQL(
        List(
          TimeSegment("2014-03-04", "2014-04-05"),
          TimeSegment("2012-05-06", "2013-01-01")
        )
      )
      sql shouldBe SQLPart("(timeSegments < '2014-04-05' AND timeSegments > '2014-03-04') OR" +
        " (timeSegments < '2013-01-01' AND timeSegments > '2012-05-06')")
    }
  }
}
