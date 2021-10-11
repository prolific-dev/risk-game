package de.htwg.se.riskgame.model

case class LegalField(name: String) extends Field {
  override def toString: String = name
}
