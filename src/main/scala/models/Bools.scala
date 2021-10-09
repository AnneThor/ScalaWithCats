package models

import cats.{Monoid, Semigroup}

object Bools {

  implicit val booleanAndMonoid: Monoid[Boolean] =
    new Monoid[Boolean] {
      def combine(x: Boolean, y: Boolean): Boolean = x && y
      def empty: Boolean = true
    }

  implicit val booleanOrMonoid: Monoid[Boolean] =
    new Monoid[Boolean] {
      def combine(x: Boolean, y: Boolean): Boolean = x || y
      def empty = false
    }

  /* This is exclusive or, meaning they are different */
  implicit val booleanXORMonoid: Monoid[Boolean] =
    new Monoid[Boolean] {
      def combine(x: Boolean, y: Boolean): Boolean =
        x == !y || !x == y
      def empty = false
    }

  /* This is exclusive nor, meaning negation of exclusive or */
  implicit val booleanXNORMonoid: Monoid[Boolean] =
    new Monoid[Boolean] {
      def combine(x: Boolean, y: Boolean): Boolean =
        !x || y && x || !y
      def empty = true
    }
}

object Sets {

  implicit def setUnionMonoid[A]: Monoid[Set[A]] =
    new Monoid[Set[A]] {
      def combine(a: Set[A], b: Set[A]): Set[A] = a union b
      def empty = Set.empty[A]
    }

  implicit def setIntersectionSemigroup[A]: Semigroup[Set[A]] =
    new Semigroup[Set[A]] {
      def combine(a: Set[A], b: Set[A]): Set[A] = a intersect b
    }

  /* symmetric difference, union less intersection is a monoid */
  implicit def setSymmetricDiffMonoid[A]: Monoid[Set[A]] =
    new Monoid[Set[A]] {
      def combine(a: Set[A], b: Set[A]): Set[A] =
        (a diff b) union (b diff a)
      def empty: Set[A] = Set.empty
    }

}
