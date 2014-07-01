package optional

trait Optional[+A] {
  def map[B](f: A => B): Optional[B]
  def flatMap[B](f: A => Optional[B]): Optional[B]
  def getOrElse[B >: A](default: => B): B
  def orElse[B >: A](ob: => Optional[B]): Optional[B]
  def filter(f: A => Boolean): Optional[A]
}

case class Some[+A](get: A) extends Optional[A] {
  override def map[B](f: (A) => B): Optional[B] = Some(f(get))

  override def flatMap[B](f: (A) => Optional[B]): Optional[B] = f(get)

  override def filter(f: (A) => Boolean): Optional[A] = if(f(get)) this else None

  override def getOrElse[B >: A](default: => B): B = get

  override def orElse[B >: A](ob: => Optional[B]): Optional[B] = this
}

case object None extends Optional[Nothing] {
  override def map[B](f: (Nothing) => B): Optional[B] = None

  override def flatMap[B](f: (Nothing) => Optional[B]): Optional[B] = None

  override def filter(f: (Nothing) => Boolean): Optional[Nothing] = None

  override def getOrElse[B >: Nothing](default: => B): B = default

  override def orElse[B >: Nothing](ob: => Optional[B]): Optional[B] = ob
}
