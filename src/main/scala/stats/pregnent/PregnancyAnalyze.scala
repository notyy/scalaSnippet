package stats.pregnent

object PregnancyAnalyze {
  case class Pregnancy(caseId:Int, prgLength: Int, outcome: Int, birthOrd: Int, finalWgt: Float)

  def extract(str: String): Pregnancy = {
    Pregnancy(
      transform(str, 0,12, _.toInt).get,
      transform(str,274,276,_.toInt).get,
      transform(str,276,277,_.toInt).get,
      transform(str,277,279,_.toInt).getOrElse(-1),
      str.substring(422,440).trim.toFloat
    )
  }

  private def transform[A](src: String,startIdx: Int, endIdx: Int, f: String => A):Option[A] = {
    val v = src.substring(startIdx, endIdx).trim
    if(v.isEmpty) None else Some(f(v))
  }

  def liveBirthCount(pregnancies: List[Pregnancy]): Int = pregnancies.count(_.outcome == 1)

  def liveBirthGroupByBirthOrder(pregnancies: List[Pregnancy]): (List[Pregnancy], List[Pregnancy]) = {
    val lives = pregnancies.filter(_.outcome >= 1)
    (lives.filter(_.birthOrd == 1), lives.filter(_.birthOrd != 1))
  }

  def avgPrgLength(pregnancies: List[Pregnancy]): Float = pregnancies.map(_.prgLength).sum / pregnancies.size
}
