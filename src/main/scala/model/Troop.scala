package de.htwg.se.riskgame.model

case class Troop(amount: Int, team: Team) {
  def this(amount: Int) = this(amount, UNKNOWN_TEAM)

  override def toString: String = amount.toString
}
