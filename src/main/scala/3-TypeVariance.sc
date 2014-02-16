/* Type Variance */

class Fruit
class Apple extends Fruit
class Orange extends Fruit

class Bucket[T]
// val bucket: Bucket[Fruit] = new Bucket[Orange] // don't compile, types w/type parameters are invariant by default

class Bag[+T]
val bag: Bag[Fruit] = new Bag[Orange] // covariance, Fruit and Orange are related Fruit is supertype of Orange
                                      // 'bag' takes any subtype Bag of Fruit

class Sack[-T]
val sack: Sack[Apple] = new Sack[Fruit] // contravariance, Fruit and Apple are related Apple is subtype of Fruit
                                        // 'sack' takes and supertype Sack of Apple


// Type variance expresses relation of types, if the types are anyhow related or not
// By default types in Scala are invariant. In Java are covariant.

// Most immutable collections are covariant:
val apples: List[Apple] = new Apple :: Nil
val oranges: List[Orange] = new Orange :: Nil
apples ++ oranges // result type will be List[Fruit]

// Most mutable collections are invariant:
// val array: Array[Fruit] = Array[Apple](new Apple) // don't compile
// a(0) = "" // if above statement will be valid, ArrayStoreException!

// Type parameter cannot be omitted:
// val list = new List() // don't compile, type parameter is missing
val list = List() // works, while List companion object is called

// Hint: Covariance is useful on class/type level. Contravariance on the method level.

class Cell[+T](init: T) {
  private[this] var current = init
  def set(x: T) { current = x }
}

val appleCell = new Cell[Apple](new Apple)
val fruitCell: Cell[Fruit] = appleCell
fruitCell.set(new Apple)







