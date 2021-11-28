package de.htwg.se.riskgame.model

import scala.util.{Failure, Success, Try}

case class Neighbors(row: Int, col: Int, fields: Matrix[Field]) {
  val neighborMap: Map[String, Option[Field]] = Map(
    "N" -> {
      Try(fields.field(row - 1, col)) match {
        case Success(field) => Some(field)
        case Failure(exception) => None
      }
    },
    "NE" -> {
      Try(fields.field(row - 1, col + 1)) match {
        case Success(field) => Some(field)
        case Failure(exception) => None
      }
    },
    "E" -> {
      Try(fields.field(row, col + 1)) match {
        case Success(field) => Some(field)
        case Failure(exception) => None
      }
    },
    "SE" -> {
      Try(fields.field(row + 1, col + 1)) match {
        case Success(field) => Some(field)
        case Failure(exception) => None
      }
    },
    "S" -> {
      Try(fields.field(row + 1, col)) match {
        case Success(field) => Some(field)
        case Failure(exception) => None
      }
    },
    "SW" -> {
      Try(fields.field(row + 1, col - 1)) match {
        case Success(field) => Some(field)
        case Failure(exception) => None
      }
    },
    "W" -> {
      Try(fields.field(row, col - 1)) match {
        case Success(field) => Some(field)
        case Failure(exception) => None
      }
    },
    "NW" -> {
      Try(fields.field(row - 1, col - 1)) match {
        case Success(field) => Some(field)
        case Failure(exception) => None
      }
    })

  def availableFriendlies(team: Team): Map[String, Option[Field]] =
    neighborMap.map((k: String, v: Option[Field]) => if (definedAndSameTeam(v, team)) (k -> v) else (k -> None))

  private def definedAndSameTeam(v: Option[Field], team: Team): Boolean =
    v.isDefined && v.get.isInstanceOf[OccupiedField] && v.get.team().equals(team)

  def availableEnemies(team: Team): Map[String, Option[Field]] =
    neighborMap.map((k: String, v: Option[Field]) => if (definedAndDifferentTeam(v, team)) (k -> v) else (k -> None))

  private def definedAndDifferentTeam(v: Option[Field], team: Team): Boolean =
    v.isDefined && v.get.isInstanceOf[OccupiedField] && !v.get.team().equals(team)

  def center(): Field = fields.field(row, col)

  def valid(): Boolean = atLeastOneReachableNeighbor()
  //|| (neighborMap.values.count(n => !n.isDefined) > 5)

  private def atLeastOneReachableNeighbor(): Boolean =
    neighborMap.values.exists(n => n.isDefined && n.get.isInstanceOf[OccupiedField])

}
