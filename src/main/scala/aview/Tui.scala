package de.htwg.se.riskgame.aview

import de.htwg.se.riskgame.model.*

class Tui {

  def processInputLine(input: String, desk: Desk): Desk = {
    input match {
      case "q" => desk
      case "n" => new Desk(3)
      case "r" => new DeskCreator(3, Seq(Team.BLUE, Team.RED)).createRandom(6)
      case "s" => desk.set(0, 0, new LegalField("Field", new Troop(3, Team.BLUE)))
      case "c on" => desk.setColorizedOn()
      case "c off" => desk.setColorizedOff()
    }
  }
}
