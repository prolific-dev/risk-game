package de.htwg.se.riskgame.model

case class IllegalField() extends Field {
  override def isSet(): Boolean = true

  override def team(): Team = NO_TEAM

  override def toString: String = "x"
}
