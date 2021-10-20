package de.htwg.se.riskgame.model

case class BlockedField() extends IField {

  override def getTroop(): Option[Troop] = None

  override def isSet(): Boolean = true

  override def team(): Team = Team.NO_TEAM

  override def toString: String = "x"
}
