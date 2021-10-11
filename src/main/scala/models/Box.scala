package models

import algebras.{CustomCodec, Printable}

final case class Box[A](value: A)

object Box {
  implicit def printableBox[A](implicit p: Printable[A]): Printable[Box[A]] =
    p.contramap[Box[A]](_.value)

  implicit def boxCodec[A](implicit c: CustomCodec[A]): CustomCodec[Box[A]] =
    c.imap[Box[A]](Box(_), _.value)

}
