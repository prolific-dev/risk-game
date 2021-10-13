package de.htwg.se.riskgame.model

case class IllegalField() extends Field {
  def team(): Team = NO_TEAM

  override def toString: String = "x"
}
