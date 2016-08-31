package variants

object Chef extends App {
  val fruit = new Fruit
  val apple = new Apple

  def cutFruit: Fruit => FruitPiece = { fruit =>
     println(s"cutting ${fruit.name}")
     new FruitPiece
  }

  def cutApple: Apple => ApplePiece = {apple =>
    println(s"cutting ${apple.name}")
    new ApplePiece
  }

  def cutIt(f: Apple => FruitPiece): FruitPiece = f(apple)

  cutIt(cutFruit)
  cutIt(cutApple)
}
