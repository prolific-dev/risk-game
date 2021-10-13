package de.htwg.se.riskgame.model

case class Troop(amount: Int, team: Team) {
  def this(amount: Int) = this(amount, NO_TEAM)

  override def toString: String = amount.toString
}
