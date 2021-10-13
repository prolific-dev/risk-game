package de.htwg.se.riskgame.model

import scala.io.AnsiColor.*

sealed trait Team(name: String, ansiColor: String, order: Int) {
  def getName: String = name

  def getAnsi: String = ansiColor

  def getOrder: Int = order
}

case object NO_TEAM extends Team("No Team", "", 0)

case object BLUE_TEAM extends Team("Blue", BLUE, 1)

case object RED_TEAM extends Team("Red", RED, 2)
