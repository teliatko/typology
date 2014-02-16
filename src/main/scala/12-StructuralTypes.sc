/* Structural Types */

// Lets take java.io.Closeable
// it's body is: { def close(): Unit }

// Class does not implement Closeable
class SomethingCloseable {
  def close(): Unit = ()
}

// Method taking a Structural Type
def closeQuietly(closeable: { def close(): Unit }) = // anything with close method
  try {
    closeable.close()
    println("It's closed dude...")
  } catch {
    case ex: Exception => // ignore...
  }

// What is does
closeQuietly(new java.io.StringReader("example"))
closeQuietly(new SomethingCloseable)

// Structural typing is so called "type-safe duck typing"
// Common question for type system is:
//    "does it implement interface X?"
// ST allows type system ask:
//    "does it have a method with this signature?"
//
// Usage: When working with unrelated libs with similar behavior but no type system hierarchy relation.
// No need to introduce wrappers/adapters to unify the type hierarchy
//
// Beware: actually has huge (negative) runtime performance implications, as it is actually implemented using reflection.

// Hint: use with type aliases, it's recommended style of using ST
// It allows you to define ST with more methods
type Closable = {
  def close(): Unit
  // def process(): Unit
}

def closeQuietly(closeable: Closable) = ???



