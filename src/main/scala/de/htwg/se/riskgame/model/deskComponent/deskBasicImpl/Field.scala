package de.htwg.se.riskgame.model.deskComponent.deskBasicImpl

import de.htwg.se.riskgame.model.teamComponent.Team
import de.htwg.se.riskgame.model.troopComponent.Troop

import scala.io.AnsiColor
import scala.io.AnsiColor.*

trait Field {
  def highlightOn: Field

  def highlightOff: Field

  def isSet: Boolean

  def getName: String

  def getTroop: Option[Troop]

  def team: Team

  def getHighlight: Boolean

  override def toString: String
}

object Field {
  def apply(kind: String): Field = kind match
    case "x" => new BlockedField
    case "free" => new OccupiedField()

  def apply(name: String, troop: Troop) = new OccupiedField(name, troop)
}

private case class BlockedField() extends Field {

  override def highlightOn: Field = this

  override def highlightOff: Field = this

  override def getTroop: Option[Troop] = None

  override def isSet: Boolean = true

  override def team: Team = Team.NO_TEAM

  override def toString: String = getName

  override def getName: String = "x"

  override def getHighlight: Boolean = false
}

private case class OccupiedField(name: String, troop: Troop, highlight: Boolean) extends Field {
  def this(name: String, troop: Troop) = this(name, troop, false)

  def this(name: String) = this(name, new Troop(1))

  def this() = this("Free Field")

  def highlightOn: OccupiedField = copy(highlight = true)

  def highlightOff: OccupiedField = copy(highlight = false)

  override def getTroop: Option[Troop] = Some(troop)

  override def getName: String = name

  override def isSet: Boolean = if (troop.team == Team.NO_TEAM) false else true

  override def toString: String =
    if (highlight) return AnsiColor.YELLOW + troop.toString + RESET
    team match
      case Team.NO_TEAM => troop.toString
      case _ => team.getAnsi + troop.toString + RESET

  override def team: Team = troop.team

  override def getHighlight: Boolean = highlight
}
