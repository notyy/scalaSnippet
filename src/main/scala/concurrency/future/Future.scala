package concurrency.future

trait Future[T] {
  def onSuccess[U](f: T => U)

  def onFailure[U](f: Exception => U)

  def flatMap[U](f: T => Future[U]): Future[U]

  def recoverWith[U](f: T => Future[U]): Future[U]

  def firstCompletedOf(futures: Seq[Future[T]]): Future[T]
}
