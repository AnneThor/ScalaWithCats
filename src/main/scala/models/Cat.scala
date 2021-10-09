package models

import algebras.Printable
import algebras.PrintableInstances.{intPrinter, stringPrinter}
import cats.{Eq, Show}
import cats.implicits._

final case class Cat(
    name: String,
    age: Int,
    color: String
)

object Cat {
  implicit val CatPrinter: Printable[Cat] =
    new Printable[Cat] {
      def format(cat: Cat): String = {
        val name: String = Printable.format(cat.name)
        val age: String = Printable.format(cat.age)
        val color: String = Printable.format(cat.color)
        s"$name is a(n) $age year-old $color cat. "
      }
    }

  implicit val catShow: Show[Cat] = Show.show[Cat](cat => {
    val name: String = cat.name.show
    val age: String = cat.age.show
    val color: String = cat.color.show
    s"$name is a(n) $age year old $color cat"
  })

  implicit val catEq: Eq[Cat] =
    Eq.instance[Cat] { (cat1, cat2) =>
      {
        cat1.name === cat2.name &&
        cat1.age === cat2.age &&
        cat1.color === cat2.color
      }
    }

}
