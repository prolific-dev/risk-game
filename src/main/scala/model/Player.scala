package de.htwg.se.riskgame.model

case class Player(name: String, team: Team) {
  def this(name: String) = this(name, NO_TEAM)

  override def toString: String = name
}
