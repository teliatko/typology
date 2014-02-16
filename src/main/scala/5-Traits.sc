/* Traits */

// Interfaces with implementation
class Base { def b = "" }
trait Cool { def c = "" }
trait Awesome { def a ="" }

class BA extends Base with Awesome
class BC extends Base with Cool

// as you might expect, you can upcast these instances into any of the traits they've mixed-in
val ba: BA = new BA
val bc: Base with Cool = new BC

val b1: Base = ba
val b2: Base = bc

ba.a
bc.c
b1.b

// Linearization
trait A { def common = "A" }

trait B extends A { override def common = "B" }
trait C extends A { override def common = "C" }

class D1 extends B with C with A
class D2 extends C with B

(new D1).common == "C"
(new D2).common == "B"

// - start building a list of types, the first element is the type we’re linearizing right now,
// - expand each supertype recursively and put all their types into this list (it should be flat, not nested),
// - remove duplicates from the resulting list, by scanning it from the left, and removing a type that you’ve already "seen"

// start with D1:
// B with C with <D1>
// expand all the types until you reach Any for all of them:
// (Any with AnyRef with A with B) with (Any with AnyRef with A with C) with <D1>
// remove duplicates by removing "already seen" types, when moving left-to-right:
// (Any with AnyRef with A with B) with (C) with <D1>
// write the resulting type nicely:
// Any with AnyRef with A with B with C with <D1>


