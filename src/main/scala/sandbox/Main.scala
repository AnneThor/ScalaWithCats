package sandbox

import algebras.Printable
import cats.Show
import cats.implicits._
import models.Cat

object Main extends App {
  println("Hello " |+| "Cats!")
  println(Printable.print(Cat("white worm", 10, "white")))

  val showInt: Show[Int] = Show.apply[Int]
  val shownInt: String = 123.show
  val showString: Show[String] = Show.apply[String]
  val intAsString = showInt.show(234)
  val stringAsString = showString.show("Hello World!")

  println(intAsString)

  val showCat = Cat("Show Cat", 1, "Fancy")
  val fancyCat = Cat("Show Cat", 1, "Fancy")
  val homeCat = Cat("Home Cat", 1, "regular")
  println(showCat.show)
  println(showCat === homeCat)
  println(showCat === fancyCat)

}
