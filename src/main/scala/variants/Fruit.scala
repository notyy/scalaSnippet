package variants

class Fruit{
  val name: String = "Fruit"
}
class FruitPiece{
  val name: String = "FruitPiece"
}
class Apple extends Fruit{
  override val name: String = "Apple"
}
class ApplePiece extends FruitPiece{
  override val name: String = "ApplePiece"
}
