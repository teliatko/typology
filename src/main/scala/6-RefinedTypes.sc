/* Refined Types */

// Subclassing without naming the subclass
// In Java known as anonymous classes

class Entity

trait Persister {
  def doPersist(e: Entity): Unit = {
    // do nothing
  }
}

// our refined instance (and type):
val refinedMockPersister = new Persister {
  override def doPersist(e: Entity) = ()
}


