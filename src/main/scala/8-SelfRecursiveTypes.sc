/* SelfRecursiveTypes */

trait Fruit[T <: Fruit[T]] {
  final def compareTo(other: Fruit[T]): Boolean = true // impl doesn't matter in our example
}

class Apple  extends Fruit[Apple]
class Orange extends Fruit[Orange]

val apple = new Apple
val orange = new Orange

// apple compareTo orange // don't compile, cannot compare apples w/oranges
