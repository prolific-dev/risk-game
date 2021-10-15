package de.htwg.se.riskgame.model

trait Field {
  def isSet(): Boolean

  def team(): Team

  override def toString: String
}
