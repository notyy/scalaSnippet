package streaming

object CellStream extends App {

  case class Cell(state: Int) {
    def update(f: Int => Int) = Cell(f(state))
  }

  def cellStream(startFrom: Cell, updateFunc: Int => Int): Stream[Cell] = startFrom #:: {
    var curr = startFrom
    Stream.continually {
      val rs = curr.update(updateFunc)
      curr = rs
      rs
    }
  }

  println(cellStream(Cell(1), _ + 1))
  cellStream(Cell(1), _ + 1).take(10).foreach(println)
}

