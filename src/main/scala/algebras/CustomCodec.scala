package algebras

trait CustomCodec[A] { self =>
  def encode(value: A): String
  def decode(value: String): A
  def imap[B](dec: A => B, enc: B => A): CustomCodec[B] = {
    new CustomCodec[B] {
      override def encode(value: B): String = self.encode(enc(value))
      override def decode(value: String): B = dec(self.decode(value))
    }
  }
}

object CustomCodec {
  def encode[A](value: A)(implicit c: CustomCodec[A]): String = c.encode(value)

  def decode[A](value: String)(implicit c: CustomCodec[A]): A =
    c.decode(value)
}

object CustomCodecInstances {

  implicit val stringCodec: CustomCodec[String] =
    new CustomCodec[String] {
      def encode(value: String): String = value
      def decode(value: String): String = value
    }
}
