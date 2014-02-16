// Unified Type System

// Look at: unified-type-system.png

// Type inference works through whole type system
// There is no differentiation between primitive types an Objects.
// It's just another part of unified hierarchy.

import scala.collection.mutable.ArrayBuffer

class Apple
val all = ArrayBuffer[Any]()
all += 23
all += new Apple

val apples = new Apple :: Nil
val things = 23 :: apples

// Bottom types
val testSomething = true // try false too...

// What is Nothing
val someResult = if (testSomething) {
    "valid" // return type is String
  } else {
    throw new Exception("You're doomed") // return type is Nothing
  }
// Hint: Nothing extends everything
// Nothing -> ... ->   [String] -> ... -> Any

// What is Null
val anotherResult = if (testSomething) {
    "valid" // return type is String
  } else {
    null // return type is Null, null is just literal
  }
// Hint: Null extends AnyRef
// Nothing -> Null -> ... ->   [String] -> ... -> AnyRef -> Any

// Mixing AnyRef with AnyVal always leads to Any, even with null
if (false) 23 else null // return type is Any
// Hint: The only common type for both branches is Any
// Nothing -> ... -> [Int] -> ... -> AnyVal -> Any
// Nothing -> Null -> ... -> AnyRef -> Any

// Hint: in REPL you can use :type command to examine type inference
// :type if (false) 23 else null
// :type -v 23

















