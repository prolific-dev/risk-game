package de.htwg.se.riskgame.model

trait IField {
  def isSet(): Boolean

  def getName(): String

  def getTroop(): Option[Troop]

  def team(): Team

  override def toString: String
}
