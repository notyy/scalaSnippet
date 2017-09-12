package slick

import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

trait DBConfigProvider {
  def getDatabaseConfig(dbName: String): DatabaseConfig[JdbcProfile] = DatabaseConfig.forConfig(dbName)
}