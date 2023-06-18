package de.htwg.se.riskgame.clone.model.deskComponent.deskBasicImpl

case class Field (name: String, troops: Int) {

  def changeTroops(troops: Int): Field = copy(troops = troops)
  override def toString: String = troops.toString
}
