package de.htwg.se.riskgame.aview

import de.htwg.se.riskgame.controller.Controller
import de.htwg.se.riskgame.model.*
import de.htwg.se.riskgame.util.Observer

class Tui(controller: Controller) extends Observer {
  controller.add(this)
  val size = 3
  val teams = Seq(Team.BLUE, Team.RED)
  val randomFields = size * size / 4

  def processInputLine(input: String): Unit = {
    input match {
      case "q" =>
      case "n" => controller.createEmptyDesk(size)
      case "r" => controller.createRandomDesk(size, teams, randomFields)
      case "s" => controller.set(0, 0, new Field("IField", new Troop(3, Team.BLUE)))
      case "c on" => controller.setColorizedOn
      case "c off" => controller.setColorizedOff
      case _ =>
    }
  }

  override def update: Unit = println(controller.deskToString)
}
