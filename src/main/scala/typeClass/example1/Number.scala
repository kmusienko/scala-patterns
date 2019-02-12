package typeClass.example1

// This is a type class
trait Number[A] {

  def plus(x: A, y: A): A
}

object Number {

  /* the same as:
  implicit val intNumber: Number[Int] = (x: Int, y: Int) => x + y
  implicit val doubleNumber: Number[Double] = (x: Double, y: Double) => x + y
  */
  implicit object IntNumber extends Number[Int] {
    override def plus(x: Int, y: Int): Int = x + y
  }

  implicit object DoubleNumber extends Number[Double] {
    override def plus(x: Double, y: Double): Double = x + y
  }

}

object Stats {
  def plus[A: Number](x: A, y: A): A = {
    implicitly[Number[A]].plus(x, y)
  }
}

object Main extends App {

  import Stats._

  val x1: Int = 1
  val y1: Int = 2

  val x2: Double = 1.2
  val y2: Double = 2.3

  val res1 = plus(x1, y1)
  val res2 = plus(x2, y2)

  println(res1)
  println(res2)
}
