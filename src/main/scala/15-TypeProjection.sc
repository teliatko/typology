/* Type Projection */

// Type projections are similar to Path Dependent Types
// It allows you to refer to Inner type via type path
// They remove the restriction for instances of Inner classes in relation parent
// Same example as above
class Outer {
  class Inner
}
// First instance of outer and inner class
val out1 = new Outer
val in1 = new out1.Inner
// Second instance of outer and inner class
val out2 = new Outer
val in2 = new out2.Inner

// Type projection (using # instead of .)
var in: Outer#Inner = in1
in = in2 // We can now assign out2.Inner type to in variable

// Same for functions (methods too)
def doSomething(in: Outer#Inner) = { // works for all Inner instances
  println(in)
}
doSomething(in1)
doSomething(in2)

// Hint: Type projection Outer#Inner is considered as a path, you cannot import it.
//