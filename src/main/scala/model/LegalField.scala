package de.htwg.se.riskgame.model

case class LegalField(name: String, troop: Troop) extends Field {
  def this(name: String) = this(name, new Troop(1))

  override def toString: String = troop.toString
}
