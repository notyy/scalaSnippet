package scripting

import java.io.File

import com.googlecode.scalascriptengine.ScalaScriptEngine
import com.typesafe.scalalogging.StrictLogging

object ScriptLoader extends App with StrictLogging {
  logger.info("starting")
  val sourceDir = new File("src/test/script")
  val sse = ScalaScriptEngine.onChangeRefresh(sourceDir)
  val sqlGen = sse.newInstance[Service]("SQLGen")
  val sql = sqlGen.execute("xx", "yy", "sybase101", "xyz")
  logger.info(s"sqlGen: $sql")
}
