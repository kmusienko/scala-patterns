package typeClass.example2

case class Person(name: String, age: Int)

case class Restaurant(name: String, brunch: Boolean)

// In this example we use implicits to transform our case class object to a Serializer object
trait Serializer1 {

  def serialize: String
}

object Serializer1 {

  implicit def PersonToSerializer(person: Person): Serializer1 = new Serializer1 {
    override def serialize: String = s"Person: $person"
  }

  implicit def RestaurantToSerializer(restaurant: Restaurant): Serializer1 = new Serializer1 {
    override def serialize: String = s"Restaurant: $restaurant"
  }
}

// In this example we implicitly pass an appropriate instance of Serializer2[T] trait to the 'serialize' function
trait Serializer2[T] {
  def process(t: T): String
}

object Serializer2 {

  implicit object PersonSerializer extends Serializer2[Person] {
    override def process(t: Person): String = s"Person: $t"
  }

  implicit object RestaurantSerializer extends Serializer2[Restaurant] {
    override def process(t: Restaurant): String = s"Restaurant: $t"
  }

  def serialize[T](t: T)(implicit s: Serializer2[T]): String = s.process(t)
}

object Main extends App {

  import Serializer1._

  val res: String = Person("Kostya", 21).serialize
  println(res)

  import Serializer2._

  val res2: String = serialize(Restaurant("Mac", brunch = true))
  println(res2)
}
