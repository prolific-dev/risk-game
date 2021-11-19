package de.htwg.se.riskgame.model

object Field {
  def apply(kind: String): IField = kind match {
    case "x" => new BlockedField
    case "free" => new OccupiedField()
  }

  def apply(name: String, troop: Troop) = new OccupiedField(name, troop)
}

private case class BlockedField() extends IField {

  override def getTroop(): Option[Troop] = None

  override def isSet(): Boolean = true

  override def team(): Team = Team.NO_TEAM

  override def toString: String = getName()

  override def getName(): String = "x"
}

private case class OccupiedField(name: String, troop: Troop) extends IField {
  def this(name: String) = this(name, new Troop(1))

  def this() = this("Free Field")

  override def getTroop(): Option[Troop] = Some(troop)

  override def getName(): String = name

  override def isSet(): Boolean = if (troop.team != Team.NO_TEAM) true else false

  override def team(): Team = troop.team

  override def toString: String = troop.toString
}
