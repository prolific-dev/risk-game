package de.htwg.se.riskgame.model

import scala.util.Random

class DeskCreateRandomStrategy(teams: Seq[Team]) extends DeskCreateStrategyTemplate {

  override def createDesk(size: Int): Desk = super.createDesk(size)

  override def prepare(size: Int): Desk = super.prepare(size)

  override def fill(_desk: Desk): Desk = {
    var desk = new Desk(_desk.size)

    for {
      x <- 0 until desk.size
      y <- 0 until desk.size
    } {
      val setFieldProbabiltyThreshold = Random.between(0, 101)
      val chosenTeamProbability = Random.between(0, teams.size)
      val chosenTeam = teams(chosenTeamProbability)

      setFieldProbabiltyThreshold match {
        case i if (i <= 35) => desk = desk.set(x, y, new OccupiedField(chosenTeam.getName, new Troop(3, chosenTeam)))
        case _ =>
      }
    }
    desk
  }
}
