package models

import cats.Monoid
import cats.implicits._

class SuperAdder {

  def add(items: List[Int]): Int = items.sum
  // here's the implementation with Monoid
  // def add(items: List[Int]:Int =
  //  items.foldLeft(Monoid[Int].empty)(_ |+| _)

  def addOption(items: List[Option[Int]]): Option[Int] =
    items.foldLeft(Monoid[Option[Int]].empty)(_ |+| _)

  // generic solution

  def addAnything[A](items: List[A])(implicit M: Monoid[A]): A =
    items.foldLeft(M.empty)(_ |+| _)

  // generic solution but shorter w / context bound

  def superAdder[A: Monoid](items: List[A]): A =
    items.foldLeft(Monoid[A].empty)(_ |+| _)

  case class Order(totalCost: Double, quantity: Double)

  implicit def orderMonoid: Monoid[Order] =
    new Monoid[Order] {
      def combine(a: Order, b: Order): Order =
        Order(a.totalCost + b.totalCost, a.quantity + b.quantity)
      def empty: Order = Order(0.0, 0.0)
    }

}
