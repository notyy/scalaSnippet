package slick

import slick.jdbc.{H2Profile, JdbcProfile, OracleProfile}

trait DBConfigProvider {
  val jdbcProfile: JdbcProfile
  val db: jdbcProfile.backend.Database
}

trait OracleDB extends DBConfigProvider {
  val jdbcProfile: JdbcProfile = OracleProfile
}

trait H2DB extends DBConfigProvider {
  val jdbcProfile: JdbcProfile = H2Profile
  override val db: jdbcProfile.backend.DatabaseDef = jdbcProfile.api.Database.forConfig("h2mem1")
}