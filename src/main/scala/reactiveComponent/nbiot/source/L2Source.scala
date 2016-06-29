package reactiveComponent.nbiot.source

import reactiveComponent.framework.Source
import reactiveComponent.nbiot.flow.CellId

trait L2Message
case class UL_CCCH_MSG(content: String, cellId: CellId) extends L2Message

class L2Source[T] extends Source[T]{

  private var buffer: Seq[T] = Nil

  def this(msgs: T *) = {
    this()
    buffer = msgs.toSeq
  }

  //will block current thread, if there is no item remains in Source.
  override def next: T = {
    Thread.sleep(1000)
    val rs = buffer.head
    buffer = buffer.tail
    rs
  }
}

object L2Source {
  def apply(msgs: String *): L2Source[L2Message] = new L2Source[L2Message]()
}
