/* Path Dependent Type */

// It allows us to check on a type that is internal to another class
class Outer {
  class Inner
}
// Let's create a first outer class with it's inner class instance
val out1 = new Outer
val in1 = new out1.Inner
// Now we create yet another outer class instance with its inner class instance
val out2 = new Outer
val in2 = new out2.Inner

// We'll assign the in1 instance to variable (check the inferred type)
var in = in1
// in = in2 // don't compile, while each Outer class has its own Inner class in scala
// Type of 'in' is variable is dependent on the path we use to get there.

// Usage
class Parent {
  class Child
}

class ChildrenContainer(val p: Parent) {
  type ChildOfThisParent = p.Child

  def add(c: ChildOfThisParent) = ???
}
// Using the path dependent type we have now encoded in the type system,
// that this container should only contain children of this parent - and not "any parent"

// Hint: Consider a type such as out1.Inner or Outer.Inner.
// It is considered as a path. Each part of the path has to be stable at the time of compilation.
// val in3: Outer.Inner // don't compile, while Inner is not stable, it is related to instance of Outer and we do not have one
var out3 = new Outer
// val in4 = out3.Inner // don't compile, while out3 is var, thus can be changed and the type for in4 cannot be inferred

