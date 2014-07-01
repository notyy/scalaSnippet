package sqlGen

import sqlGen.Domain.{SQLPart, TimeSegment}

trait TimeSegmentSQLGenerator {
  def genTimeSegmentsSQL(segments: List[TimeSegment]): SQLPart = {
    SQLPart(segments.map(genSingleTimeSegmentSQL).reduce((s1,s2) => s"$s1 OR $s2"))
  }

  private def genSingleTimeSegmentSQL(segment: TimeSegment): String = {
    val TimeSegment(start, end) = segment
    s"(timeSegments < '$end' AND timeSegments > '$start')"
  }
}
