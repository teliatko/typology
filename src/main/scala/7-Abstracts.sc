/* Abstract Type Member */

trait Container {
  type A
  def value: A
}

object IntContainer extends Container {
  type A = Int
  val value = 42
}

type User = String
type Age = Int

val data:  Map[User, Age] =  Map.empty
