import scala.util.Random

for (i <- 0 to 100) {
  println(new Random().between(0, 2))
}
