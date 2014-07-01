package sqlGen

import sqlGen.Domain.SQLPart

trait GranularitySQLGenerator {
  def genGranularitySQL: SQLPart
}

trait RNCGranularitySQLGenerator extends GranularitySQLGenerator{
  override def genGranularitySQL: SQLPart = SQLPart("'RNC'")
}

trait CELLGranularitySQLGenerator extends GranularitySQLGenerator{
  override def genGranularitySQL: SQLPart = SQLPart("'CELL'")
}