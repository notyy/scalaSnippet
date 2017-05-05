package expressiveness.typeclass

import expressiveness.typeclass.ShapeModule.{Circle, Square}
import org.scalatest.{FunSpec, Matchers}

class ClientTest extends FunSpec with Matchers{
  describe("Client"){
    it("should print area of shape"){
      Client.printArea(Square(5.0))
      Client.printArea(Circle(5.0))
    }
    it("should print perimeter of shape"){
      Client.printPerimeter(Square(5.0))
//      Client.printPerimeter(Circle(5.0))
    }
  }

}
