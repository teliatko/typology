package examples

// Normal class to show how primitives are used by compiler
class UnifiedTypeSystem(value: Int) {
  def doSomething: Int = 23
}
// use :javap -c example.UnifiedTypeSystem
