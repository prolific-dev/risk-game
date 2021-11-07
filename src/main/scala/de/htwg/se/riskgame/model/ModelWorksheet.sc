import de.htwg.se.riskgame.model.*

val dc = new DeskCreator(8, Seq(Team.BLUE, Team.RED)).createRandom(30)

dc.toString
