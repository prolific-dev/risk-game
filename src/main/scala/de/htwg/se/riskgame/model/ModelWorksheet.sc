import de.htwg.se.riskgame.model.*

val dc = new DeskCreateRandomStrategy(8, Seq(Team.BLUE, Team.RED)).createRandom(30)

dc.toString
