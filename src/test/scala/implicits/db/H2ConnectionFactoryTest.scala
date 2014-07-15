package implicits.db

import org.scalatest.FunSpec

class H2ConnectionFactoryTest extends FunSpec with H2ConnectionFactory{
  describe("H2ConnectionFactory") {
    it("can create connection to h2 database") {
      val conn = createConnection
//      conn.
    }
  }
}
