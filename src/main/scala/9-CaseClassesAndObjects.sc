/* Case Classes And Case Objects */

import examples._

// What it does
val circle = new Circle(5) // starting with normal class
// circle.radius // don't compile, without 'val' keyword radius is private

val rect1 = Rectangle(1, 2) // no 'new' keyword
val rect2 = Rectangle(1, 2) // no need for 'val' keyword for constructor parameters

rect1          // compiler generates toString
rect1 == rect2 // ... equals, hashCode

val anotherRect = rect1.copy(3, 4)  // and all-mighty copy method
rect1 == rect2                      // instances are immutable
anotherRect != rect1

val yetAnotherRect = rect1.copy(width = 4) // copy allows to use just few fields
yetAnotherRect != rect1                    // copies still do not equal to original

// Case classes and objects represents project types
//
// Usage: For seasoned Java developer it's replacement for ValueObjects/DataTransferObjects,
// but they are more powerful:
// - immutable
// - serializable
// - with hashCode, equals and toString
// - matchable, compiler generates extractor for pattern matching

var inQuestion: Any = TheOnlyOne
inQuestion match {
  case Rectangle(x, _) => println(x)
  case TheOnlyOne => println("There's nothing like case class in Java ... uff")
}






