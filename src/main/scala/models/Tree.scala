package models

import cats.Functor

sealed trait Tree[+A]

final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]
final case class Leaf[A](value: A) extends Tree[A]

/* Write a functor for tree data type, verify it works on both branch/leaf */

object Tree {
  implicit def treeFunctor: Functor[Tree] =
    new Functor[Tree] {
      def map[A, B](tree: Tree[A])(fa: A => B): Tree[B] =
        tree match {
          case Branch(l, r) => Branch(map(l)(fa), map(r)(fa))
          case Leaf(v)      => Leaf(fa(v))
        }
    }

  /* Added smart constructors here in order to access the syntax */
  def branch[A](l: Tree[A], r: Tree[A]): Tree[A] =
    Branch(l, r)

  def leaf[A](value: A): Tree[A] = Leaf(value)

}
