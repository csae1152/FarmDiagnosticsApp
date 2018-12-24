package helpers

trait Monoid[A] extends Semigroup[A] { self =>
  ////
  /** The identity element for `append`. */
  def zero: A


}

trait Semigroup[A]  { self =>
  def append(a1: A, a2: => A): A

}
