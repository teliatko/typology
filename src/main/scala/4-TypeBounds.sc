/* Type Bounds */

// Defines restrictions on types relations
// T <: UpperBound, T >: LowerBound, T <% ViewBound, T : ContextBound

// Having a plain type T, does not work
class Pair[T](val first: T, val second: T) {
  // def smaller if (first.compareTo(second) < 0) first else second // don't compile
}
// Using upper bound we can express a constraint on T
class UpperBoundPair[T <: Comparable[T]](val first: T, val second: T) { // T must be subtype of Comparable[T]
  def smaller = if (first.compareTo(second) < 0) first else second
}

// Now we can create an instance of ConstraintedPair[String]
val p = new UpperBoundPair("Me", "You")
p.smaller
class Anything
// new UpperBoundPair(new Anything, new Anything) // don't compile, Anything is not subclass of Comparable

// Just add another method to replace first value in pair
class LowerBoundPair[T](val first: T, val second: T) {
  def replaceFirstNaive(newFirst: T) = new LowerBoundPair(newFirst, second)
  def replaceFirst[R >: T](newFirst: R) = new LowerBoundPair(newFirst, second)
  def replaceFirstPoly[R](newFirst: R) = new LowerBoundPair(newFirst, second)
}
class Person(val name: String)
class Student(override val name: String) extends Person(name)
val p2 = new LowerBoundPair(new Student("Me"), new Student("You"))
val person = new Person("Stranger")
// p2.replaceFirstNaive(person) // don't compile Student is required
p2.replaceFirst(person) // Even when our type is invariant, inferred type is LowerBoundPair[Person]
p2.replaceFirstPoly(person) // Omitting the lower-bound infers LowerBoundPair[Any]
// Lower type bounds are best used on contravariant positions, i.e. in methods

// Our class UpperBoundPair is not applicable to Int
// new UpperBoundPair(2, 4) // don't compile, while int, java.lang.Int and Int wrapper type does not implement Comparable
// but RichInt does and there is an implicit conversion from Int to RichInt
// Using view bound we can express that T can be converted to Comparable[T]
class ViewBoundPair[T <% Comparable[T]](val first: T, val second: T) {
  def smaller = if (first.compareTo(second) < 0) first else second
}
new ViewBoundPair(24, 12).smaller

// In following, we will be using Ordering instead of Comparable
// View bound requires an existence of implicit conversion from T to Ordering[T]
// An context bound just requires and existence of implicit value of Ordering[T]
// We say that for type T exists implicit value of type Ordering[T]
class ContextBoundPair[T : Ordering](val first: T, val second: T) {
  def smaller(implicit ord: Ordering[T]) =
    if (ord.compare(first, second) < 0) first else second
}
val p3 = new ContextBoundPair("Me", "You") // implicit value for string Ordering is in scala.math and included in Predef
p3.smaller

// When you want to use custom classes with ContextBoundPair
// You have to provide an implicit Ordering value, e.g. for Person
implicit val ord = new Ordering[Person] {
  override def compare(x: Person, y: Person): Int = x.name.compareTo(y.name)
}
val p4 = new ContextBoundPair(new Person("Me"), new Person("You"))
p4.smaller.name
// Context bounds are available from Scala 2.8 and use so called 'type class pattern' (dedicated section)

// Using Manifest context bound helps you to force the compiler to work with primitives in case of generic classes
// Array is a generic type, due type erasue on JVM, there is no way to distinguish that Array for Int has to be int[] on JVM
def makePairGeneric[T](first: T, second: T) = {
  val arr = new Array[T](2)(); arr(0) = first; arr(1) = second; arr
}
// Using context bound with manifest compiler has an information on call point and can
// generate optimized version for primitive types
def makePairGeneric[T : Manifest](first: T, second: T) = {
  val arr = new Array[T](2)(); arr(0) = first; arr(1) = second; arr
  // Array(first, second) // does the same effect, while apply method takes context bound ClassTag
}

// Using multiple bounds is possible too
// T <: Upper :> Lower
// You can't have multiple lower and upper bounds, however you can still require that T implements multiple traits
// T <: Upper with AnotherUpper with YetAnotherUpper
// You can have more than one view bound
// T <% View <% AnotherView
// And also have multiple context bounds
// T : Context : AnotherContext

















