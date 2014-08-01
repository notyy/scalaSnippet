import de.johoop.jacoco4sbt._
import JacocoPlugin._
import sbt._

// set the name of the project
name := "sbtTemplate"

version := "0.0.1"

organization := "com.github.notyy"

// set the Scala version used for the project
scalaVersion := "2.10.4"

resolvers ++= Seq(
  "Sonatype Releases" at "http://oss.sonatype.org/content/repositories/releases",
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
)

libraryDependencies ++= Seq(
  "org.scalacheck" %% "scalacheck" % "1.11.3" % "test,it,ft",
  "org.pegdown" % "pegdown" % "1.0.2" % "test,it,ft", //used in html report
  "org.scalatest" % "scalatest_2.10" % "2.1.0" % "test,it,ft",
  "org.slf4j" % "slf4j-api" % "1.7.7",
  "com.storm-enroute" %% "scalameter" % "0.6" % "test",
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "junit" % "junit" % "4.11" % "test",
  "com.h2database" % "h2" % "1.3.148",
  "org.mockito" % "mockito-all" % "1.9.5" % "test"
)

testFrameworks += new TestFramework("org.scalameter.ScalaMeterFramework")

logBuffered := false

val helloTask = TaskKey[Unit]("hello", "Print hello")

helloTask := println("hello world")

// reduce the maximum number of errors shown by the Scala compiler
maxErrors := 20

// increase the time between polling for file changes when using continuous execution
pollInterval := 1000

// append several options to the list of options passed to the Java compiler
javacOptions ++= Seq("-source", "1.6", "-target", "1.6")

// append -deprecation to the options passed to the Scala compiler
scalacOptions += "-deprecation"

// set the initial commands when entering 'console' only
// initialCommands in console := "import com.kaopua.recall._"

// set the main class for packaging the main jar
// 'run' will still auto-detect and prompt
// change Compile to Test to set it for the test jar
// mainClass in (Compile, packageBin) := Some("com.kaopua.recall.Main")

// set the main class for the main 'run' task
// change Compile to Test to set it for 'test:run'
// mainClass in (Compile, run) := Some("com.kaopua.recall.Main")

// set the main class for the main 'test:run' task
// mainClass in (Test, run) := Some("com.kaopua.recall.Main")

// disable updating dynamic revisions (including -SNAPSHOT versions)
offline := true

// set the prompt (for this build) to include the project id.
shellPrompt in ThisBuild := { state => Project.extract(state).currentRef.project + "> " }

// set the prompt (for the current project) to include the username
shellPrompt := { state => System.getProperty("user.name") + "> " }

// disable printing timing information, but still print [success]
showTiming := false

// disable printing a message indicating the success or failure of running a task
showSuccess := false

// change the format used for printing task completion time
timingFormat := {
    import java.text.DateFormat
    DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT)
}

// only use a single thread for building
parallelExecution := false


// Use Scala from a directory on the filesystem instead of retrieving from a repository
//scalaHome := Some(file("/home/user/scala/trunk/"))

// don't aggregate clean (See FullConfiguration for aggregation details)
aggregate in clean := false

// only show warnings and errors on the screen for compilations.
//  this applies to both test:compile and compile and is Info by default
logLevel in compile := Level.Info

// only show warnings and errors on the screen for all tasks (the default is Info)
//  individual tasks can then be more verbose using the previous setting
logLevel := Level.Info

// only store messages at info and above (the default is Debug)
//   this is the logging level for replaying logging with 'last'
persistLogLevel := Level.Info

// only show 10 lines of stack traces
traceLevel := 10

exportJars := true

// only show stack traces up to the first sbt stack frame
traceLevel := 0

// add SWT to the unmanaged classpath
// unmanagedJars in Compile += file("/usr/share/java/swt.jar")

// seq(oneJarSettings: _*)

// libraryDependencies += "commons-lang" % "commons-lang" % "2.6"

jacoco.settings

// Execute tests in the current project serially
//   Tests from other projects may still run concurrently.
parallelExecution in Test := false

parallelExecution in IntegrationTest := false

parallelExecution in FuncTest := false


// create beautiful scala test report
testOptions in Test ++= Seq(
  Tests.Argument(TestFrameworks.ScalaTest,"-h","target/html-unit-test-report"),
  Tests.Argument(TestFrameworks.ScalaTest,"-u","target/unit-test-reports"),
  Tests.Argument(TestFrameworks.ScalaTest,"-o")
)

testOptions in jacoco.Config ++= Seq(
  Tests.Argument(TestFrameworks.ScalaTest,"-h","target/html-unit-test-report"),
  Tests.Argument(TestFrameworks.ScalaTest,"-u","target/unit-test-reports"),
  Tests.Argument(TestFrameworks.ScalaTest,"-o")
)

//testOptions in IntegrationTest ++= Seq(
//  Tests.Argument("-h","target/html-integration-test-report"),
//  Tests.Argument("-u","target/integration-test-reports"),
//  Tests.Argument("-o")
//)
//
//testOptions in FuncTest ++= Seq(
//  Tests.Argument("-h","target/html-function-test-report"),
//  Tests.Argument("-u","target/function-test-reports"),
//  Tests.Argument("-o")
//)