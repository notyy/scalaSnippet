package sqlGen

import sqlGen.Domain.SQLPart

trait DBConfigLoader {
  def loadConfig: SQLPart = ???
}
