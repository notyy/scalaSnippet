package reactiveComponent.framework

trait Source[T] {
  //will block current thread, if there is no item remains in Source.
  def next: T
  def isComplete: Boolean
}
