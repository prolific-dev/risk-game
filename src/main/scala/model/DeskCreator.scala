package de.htwg.se.riskgame.model

import scala.util.Random

class DeskCreator(size: Int, teams: Seq[Team]) {
  def createRandom(num: Int): Desk = {
    var desk = new Desk(size)
    for (index <- 0 until num) {
      desk = setRandomField(desk)
    }
    desk
  }

  private def setRandomField(desk: Desk): Desk = {
    val randomTeam = teams(Random.nextInt(teams.size))


    val row = Random.nextInt(desk.size)
    val column = Random.nextInt(desk.size)

    desk.field(row, column).isSet() match {
      case false => desk.set(row, column, new Field("", new Troop(3, randomTeam)))
      case true => desk
    }
  }
}
