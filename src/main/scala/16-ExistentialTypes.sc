/* Existential Types */

// Existential types were added to Scala for compatibility with Java wildcards.
// An existential type is a type expression followed by forSome { ... } where braces can contain type or val declarations
val a: Array[T] forSome { type T } = Array("Me", "You") // value of type
// As you can see shorthand for this notation is Array[_], meaning any type => wildcard
// Wildcards are just syntactic sugar
val m1: Map[_, _] = Map("Me" -> "You")
val m2: Map[K, V] forSome {type K; type V } = m1

// Notation with forSome allows to define more complex relationships than wildcard can express
val m3: Map[K, V] forSome { type K; type V <: K } = m1 // you cannot say in wildcard notation that value is subtype of key

// It's also possible to use val declarations inside forSome, because vals can have its own subtypes
class Outer {
  class Inner
}
// Let's define a method which takes any type which is subtype of same outer
def process[O <: o.Inner forSome { val o: Outer }](o1: O, o2: O) = (o1, o2)

// by definition def process[O < Outer#Inner](...) = ... you will allow to mix instances
val out1 = new Outer
val in1_1 = new out1.Inner
val in1_2 = new out1.Inner
val out2 = new Outer
val in2_1 = new out2.Inner
val in2_2 = new out2.Inner
process(in1_1, in1_2)
process(in2_1, in2_2)
// process(in1_1, in2_1) // don't compile, you cannot mix Inner instances but you can work with instances from same Outer

// You can use existential types also with type aliases
trait Cool
type CoolStuff = T forSome { type T >: Cool } // only cooler types are allowed




















