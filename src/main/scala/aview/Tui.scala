package de.htwg.se.riskgame.aview

import de.htwg.se.riskgame.model.{Desk, LegalField, Team, Troop}

class Tui {

  def processInputLine(input: String, desk: Desk): Desk = {
    input match {
      case "q" => desk
      case "n" => new Desk(3)
      case "s" => desk.set(0, 0, new LegalField("Field", new Troop(3, Team.BLUE)))
      case "c on" => desk.setColorizedOn()
      case "c off" => desk.setColorizedOff()
    }
  }
}
