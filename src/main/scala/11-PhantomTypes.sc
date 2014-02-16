/* Phantom Types */

// We have the states
trait StartStopState
trait Started extends StartStopState
trait Stopped extends StartStopState

// We have service w/state behavior
class Service[State <: StartStopState] {
  def start[T >: State <: Stopped] = // allowed only in Stopped state
    this.asInstanceOf[Service[Started]]
  def stop[T >: State <: Started] = // allowed only in Started state
    this.asInstanceOf[Service[Stopped]]
}

// And finally we have service instance
val x = new Service[Stopped]()

// What it does
x.start // works
// res0.start // don't compile, cannot be started multiple times
x.start // can by started multiple-times
res0.stop // works
// x.stop // don't compile, it's stopped already

// Usage: when designing the state behavior.
// Methods represents transitions and are enabled by state.
// When you compare this with common State pattern it's simpler.
// You can extract methods per state into traits to clarify the implementation.










