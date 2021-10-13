package de.htwg.se.riskgame.model

sealed trait Team(name: String, order: Int) {
  def getName: String = name

  def getOrder: Int = order
}

case object UNKNOWN_TEAM extends Team("Unknown", 0)

case object BLUE_TEAM extends Team("Blue", 1)

case object RED_TEAM extends Team("Red", 2)
