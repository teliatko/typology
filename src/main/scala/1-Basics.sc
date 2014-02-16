/* Basics */

// Scala has classes, objects and traits (covered in separate section)

// Classes are common classes, like those in Java
class Thing(name: String) { // Primary constructor is written right-after the class name
  def this() { // Other constructors are written via 'this' keyword
    this("Anonymous") // All constructors has to call primary one
  }
}
// They serve the purpose of blueprint for object instances

// Object are directly instances
object AllMightyThing { // They do not have constructors, while they are created when loaded
  // They are singletons
}

// Companion object is object with the same name as the class defined in same file
class Something(name: String)
object Something
// It normally contains all the behavior which is general for all instances of the class
// In plain Java they contain all static behavior

// Type inference
def getThing = new Thing // Object instance creation, from class
def getAllMightyThing = AllMightyThing // Object instance reference, there's no creation

// Compiler can infer types, you don't have to write them, the type is inferred to be `Thing`
val infered = getThing
// with Type Annotation
val thing: Thing = getThing

AllMightyThing.type // this is type of object

// Hint: where to use Type Annotations
// - Is it a parameter? Yes, you have to.
// - Is it a public methods return value? Yes, for self-documenting code and control over exported types.
// - Is it a recursive or overloaded methods return value? Yes, you have to.
// - Do you need to return a more general interface than the inferrencer would find? Yes, otherwise you’d expose your implementation details for example.
//   Else… No, don’t include a Type Annotation.

// Related hint: Including Type Annotations speeds up compilation, also it’s generally nice to see the return type of a method.

// There are also traits ... in separate chapter
