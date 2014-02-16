package examples

// Normal class
class Circle(radius: Long)

// Case class
case class Rectangle(width: Long, height: Long) // just use keyword case
// Compiler will generate Rectangle object too, checkout with :javap

// Case object
case object TheOnlyOne { // just use keyword case
  def oneAndOnlyOne = "11111....1" // either classes and objects can have additional methods
}
// Compiler will make object serializable, checkout with :javap


