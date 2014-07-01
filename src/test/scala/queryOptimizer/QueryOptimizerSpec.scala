package queryOptimizer

import org.scalatest.{ShouldMatchers, FunSpec}
import queryOptimizer.QueryOptimizer.SQL

class QueryOptimizerSpec extends FunSpec with ShouldMatchers{
  describe("QueryOptimizer"){
    it("should optimize a raw sql, generate better sql"){
      val rawSQL = SQL("SELECT name from (SELECT id,name FROM person) p WHERE p.id=1")
      QueryOptimizer.optimize(rawSQL) shouldBe SQL("SELECT name FROM person WHERE id=1")
    }
  }
}
