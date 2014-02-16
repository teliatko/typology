/* Value Classes */

import examples._

// What it does
val m = new Meter(12) // store 12.0 (plain double)
val v = m.value * 2.0      // double multiply, 12.0 * 2.0, store
val f = m.toFeet      // call Meter$.$MODULE$.toFeet$extension(12.0)

// Value classes wrap values
// Compiler deals with them as with values:
//    optimizing their usage: boxing and unboxing
// You code is clean and safe, you code against classes
// All Numbers extend from AnyVal, your types can too...
//
// Usage: Domain Modeling for IDs or ValueObjects. Cases where you want optimize code.
//
// Beware: There are many limitations http://docs.scala-lang.org/overviews/core/value-classes.html#summary_of_limitations
// Hint: Something similar like implicit conversions, but does not create wrapper to box/unbox something all the time

object SomeDomain {

  case class InvoiceId(id: Long) extends AnyVal

  case class Customer(name: String, surname: String)

  case class Invoice(
      id: InvoiceId, // You can work with meaningful types, runtime will work with primitives
      customer: Customer
    )

  trait SomeInvoiceService {
    def doSomething(id: InvoiceId) = println(id)
  }

  val service = new Object with SomeInvoiceService

}
import SomeDomain._
val id = InvoiceId(0L)  // First ever invoice
service.doSomething(id) // Call service
// service.doSomething(1L) // don't compile, you cannot pass a Long, like by type aliases








