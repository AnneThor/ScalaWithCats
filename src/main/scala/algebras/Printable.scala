package algebras

trait Printable[A] {
  def format(value: A): String
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
