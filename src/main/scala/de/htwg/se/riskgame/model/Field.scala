package de.htwg.se.riskgame.model

case class Field(name: String, troop: Troop) extends IField {
  def this(name: String) = this(name, new Troop(1))

  def this() = this("Free Field")

  override def getTroop(): Option[Troop] = Some(troop)

  override def isSet(): Boolean = if (troop.team != Team.NO_TEAM) true else false

  override def team(): Team = troop.team

  override def toString: String = troop.toString
}
