/* Self Type Annotations */

// They are used with traits in order to express trait requirements
trait Repository {
  def findById(id: Int) = "Oops"
}
trait Service {
  self: Repository =>
  def doSomething() = findById(3)
}
// val fail = new Service // don't compile, Service needs repository
val s1 = new Service with Repository // requirement satisfied
s1.doSomething() // works even it has drawback...
s1.findById(320) // all the public methods are part of Service interface

// It can be fixed with visibility
object bundle { // bundle object is here introduced only to group things together (there are no packages in sheets)
  trait Repository {
    private[bundle] def findById(id: Int) = "Oops"
  }

  trait Service {
    self: Repository =>

    def doSomething() = findById(3)
  }
}
val s2 = new bundle.Service with bundle.Repository
// s2.findById(123) // don't compile, method is private for bundle
s2.doSomething() // works as usual

// Problem is that Repository can be tested only within bundle (package in reality), while is visible only there
// It's is easier to depend on some mediator and adjust visibility there
object bundleAgain {
  trait Module {
    private[bundleAgain] val repo = new Object with Repository
  }

  trait Repository {
    def findById(id: Int) = "Oops"
  }

  trait Service {
    self: Module =>

    def doSomething() = repo.findById(3)
  }
}
val s3 = new bundleAgain.Service with bundleAgain.Module
// s3.repo // don't compile, value is private for bundleAgain
s3.doSomething() // works as usual

// Self-type annotations are base for 'cake-pattern'
// It is base for composition with types as a counterpart to inheritance
// In fact you can use any identifier not only 'self' or 'this'
// Classes can also have a self-type annotations too.
class ServiceImpl {
  oops: Repository =>
  def doSomethingImpl() = findById(1000)
}
