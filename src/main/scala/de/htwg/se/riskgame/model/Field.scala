package de.htwg.se.riskgame.model

import scala.io.AnsiColor
import scala.io.AnsiColor.*

trait Field {
  def isSet: Boolean

  def getName: String

  def getTroop: Option[Troop]

  def team: Team

  override def toString: String
}

object Field {
  def apply(kind: String): Field = kind match {
    case "x" => new BlockedField
    case "free" => new OccupiedField()
  }

  def apply(name: String, troop: Troop) = new OccupiedField(name, troop)
}

private case class BlockedField() extends Field {

  override def getTroop: Option[Troop] = None

  override def isSet: Boolean = true

  override def team: Team = Team.NO_TEAM

  override def toString: String = getName

  override def getName: String = "x"
}

private case class OccupiedField(name: String, troop: Troop, highlightOn: Boolean) extends Field {
  def this(name: String, troop: Troop) = this(name, troop, false)

  def this(name: String) = this(name, new Troop(1))

  def this() = this("Free Field")

  override def getTroop: Option[Troop] = Some(troop)

  override def getName: String = name

  override def isSet: Boolean = if (troop.team != Team.NO_TEAM) true else false

  override def toString: String = {
    if (highlightOn) {
      AnsiColor.YELLOW + troop.toString + RESET
    } else {
      team match {
        case Team.NO_TEAM => troop.toString
        case _ => team.getAnsi + troop.toString + RESET
      }
    }
  }

  override def team: Team = troop.team
}
