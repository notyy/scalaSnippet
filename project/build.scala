/**
  demostrate how to write a customer task
*/

import sbt._
import Keys._

object NotyyBuild extends Build {
  lazy val  simpleTask = taskKey[Unit]("A sample task.")
  lazy val FuncTest = config("ft") extend Test

  lazy val root =
    project.in(file("."))
      .configs( FuncTest )
      .configs( IntegrationTest )
      .settings( inConfig(FuncTest)(Defaults.testSettings) : _*)
      .settings( Defaults.itSettings : _*)
      .settings(
          simpleTask := {
            App.myGen()
          }
        )
}

