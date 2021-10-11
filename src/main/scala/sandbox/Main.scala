package sandbox

import algebras.Printable
import cats.{Functor, Show}
import cats.implicits._
import models._

object Main extends App {

  val one: Leaf[Int] = Leaf(1)
  val two: Leaf[Int] = Leaf(2)
  val three: Leaf[Int] = Leaf(3)
  val four: Leaf[Int] = Leaf(4)
  val lChild: Branch[Int] = Branch(one, two)
  val rChild: Branch[Int] = Branch(three, four)
  val root: Branch[Int] = Branch(lChild, rChild)

  val updTree: Tree[Int] = Functor[Tree].map(root)(_ * 2)
  println(updTree)

  val rootTwo: Tree[Int] = Tree.branch(Tree.leaf(1), Tree.leaf(2))
  val secondTreeUpdate: Tree[Int] = rootTwo.map(_ + 100)
  println(secondTreeUpdate)

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
