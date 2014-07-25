addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.4.0")

//code coverage
libraryDependencies ++= Seq(
  "org.jacoco" % "org.jacoco.core" % "0.6.4.201312101107" artifacts Artifact("org.jacoco.core", "jar", "jar"),
  "org.jacoco" % "org.jacoco.report" % "0.6.4.201312101107" artifacts Artifact("org.jacoco.report", "jar", "jar"))

addSbtPlugin("de.johoop" % "jacoco4sbt" % "2.1.6")

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.6.0")