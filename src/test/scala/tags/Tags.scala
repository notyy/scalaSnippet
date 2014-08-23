package tags

import org.scalatest.Tag

object Tags {
  object DbTest extends Tag("DbTest")
  object FastTest extends Tag("FastTest")
  object FunctionTest extends Tag("FunctionTest")
  object IntegrationTest extends Tag("IntegrationTest")
}
