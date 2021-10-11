package algebras

/* purpose of "self" is to distinguish inner/outer printables */
trait Printable[A] { self =>

  def format(value: A): String
  def contramap[B](f: B => A): Printable[B] =
    new Printable[B] {
      def format(value: B): String = self.format(f(value))
    }

}

object PrintableInstances {
  implicit val stringPrinter: Printable[String] =
    new Printable[String] {
      def format(value: String): String = value
    }

  implicit val intPrinter: Printable[Int] =
    new Printable[Int] {
      def format(value: Int): String = value.toString
    }

  implicit val boolPrinter: Printable[Boolean] =
    new Printable[Boolean] {
      def format(value: Boolean): String =
        if (value) "yes" else "no"
    }
}

object Printable {
  def format[A](value: A)(implicit P: Printable[A]): String = P.format(value)

  def print[A](value: A)(implicit P: Printable[A]): Unit =
    println(P.format(value))
}

object PrintableSyntax {
  implicit class PrintableOps[A](value: A) {
    def format(implicit P: Printable[A]): String = P.format(value)
    def print(implicit P: Printable[A]): Unit = println(format(P))
  }
}
