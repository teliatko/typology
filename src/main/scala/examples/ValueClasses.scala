package examples

// Normal class
class Foot(val value: Double)

// Value class
class Meter(val value: Double) extends AnyVal { // just extend AnyVal
def toFeet: Foot = new Foot(value * 0.3048)
}
// Compiler will generate Meter object too, checkout with :javap

// Another value class based on case class
case class Yard(value: Double) extends AnyVal {
  def toFeet: Foot = new Foot(value / 3.0)
}
