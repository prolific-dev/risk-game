package de.htwg.se.riskgame.aview

import de.htwg.se.riskgame.model.*

class Tui {

  def processInputLine(input: String, desk: Desk): Desk = {
    input match {
      case "q" => desk
      case "n" => new Desk(3)
    }
  }
}
