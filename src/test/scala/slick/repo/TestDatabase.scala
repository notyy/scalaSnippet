package slick.repo

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.StrictLogging
import slick.basic.DatabaseConfig
import slick.{DBConfigProvider, Database}

trait TestDatabase {
   lazy val myDatabase = TestDatabaseInstance
}

object TestDatabaseInstance extends Database with DBConfigProvider with StrictLogging {
  override def getDatabaseConfig(dbName: String) = {
    val testConfig = ConfigFactory.load("test")
    DatabaseConfig.forConfig(dbName, testConfig)
  }
}

