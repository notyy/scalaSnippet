package stats.pregnent

object PregnancyAnalyze {
  case class Pregnancy(caseId:Int, prgLength: Int, outcome: Int, birthOrd: Int, finalWgt: Float)

  def extract(str: String): Pregnancy = {
    Pregnancy(
      str.substring(0,12).trim.toInt,
      str.substring(274,276).trim.toInt,
      str.substring(276,277).trim.toInt,
      str.substring(277,279).trim.toInt,
      str.substring(422,440).trim.toFloat
    )
  }
}
