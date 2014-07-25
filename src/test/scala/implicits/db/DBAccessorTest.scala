package implicits.db

import java.sql.Connection

import org.scalatest.{Ignore, FunSpec}
import org.scalatest.mock.MockitoSugar

@Ignore
class DBAccessorTest extends FunSpec with MockitoSugar {
  trait MockConnectionFactory extends ConnectionFactory{
    val mockConnection = mock[Connection]
    override def createConnection: Connection = mockConnection
  }

  class BizClass extends H2DBAccessorWithTransactionSupport {
    def bizFunc(para: List[String]): List[List[String]] = {
      inTransaction{ conn =>
        executeQuery(SQL(""))(rsToStringTable)(conn)
      }
    }
  }

  describe("DBAccessor"){
    it("can run function inside a transaction"){
      new BizClass().bizFunc(Nil)
    }
  }
}
