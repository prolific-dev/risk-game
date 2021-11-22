import de.htwg.se.riskgame.controller.GameStatus

import scala.util.Random

for (i <- 0 to 100) {
  println(new Random().between(0, 2))
}

val e = GameStatus.IDLE

e.getClass
