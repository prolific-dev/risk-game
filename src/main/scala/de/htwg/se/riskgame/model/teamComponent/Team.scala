package de.htwg.se.riskgame.model.teamComponent

import scala.io.AnsiColor

enum Team(name: String, ansiColor: String, order: Int):

  def getName: String = name

  def getAnsi: String = ansiColor

  def getOrder: Int = order


  case NO_TEAM extends Team("No Team", "", 0)
  case BLUE extends Team("Blue", AnsiColor.BLUE, 1)
  case RED extends Team("Red", AnsiColor.RED, 2)
end Team
