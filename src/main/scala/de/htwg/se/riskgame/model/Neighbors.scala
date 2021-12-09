package de.htwg.se.riskgame.model

import scala.language.postfixOps
import scala.util.{Failure, Success, Try}

case class Neighbors(row: Int, col: Int, fields: Matrix[Field]) {
  val neighborMap: Map[String, Option[Field]] = Map(
    "N" -> {
      Try(fields.field(row - 1, col)) match
        case Success(field) => Some(field)
        case Failure(exception) => None
    },
    "NE" -> {
      Try(fields.field(row - 1, col + 1)) match
        case Success(field) => Some(field)
        case Failure(exception) => None
    },
    "E" -> {
      Try(fields.field(row, col + 1)) match
        case Success(field) => Some(field)
        case Failure(exception) => None
    },
    "SE" -> {
      Try(fields.field(row + 1, col + 1)) match
        case Success(field) => Some(field)
        case Failure(exception) => None
    },
    "S" -> {
      Try(fields.field(row + 1, col)) match
        case Success(field) => Some(field)
        case Failure(exception) => None
    },
    "SW" -> {
      Try(fields.field(row + 1, col - 1)) match
        case Success(field) => Some(field)
        case Failure(exception) => None
    },
    "W" -> {
      Try(fields.field(row, col - 1)) match
        case Success(field) => Some(field)
        case Failure(exception) => None
    },
    "NW" -> {
      Try(fields.field(row - 1, col - 1)) match
        case Success(field) => Some(field)
        case Failure(exception) => None
    })

  def center: Field = fields.field(row, col)

  def neighborCoordinates: Map[String, Option[(Int, Int)]] =
    neighborMap map {case(k, v) => if (v.isDefined) k -> (k match {
      case "N" => Some((row - 1, col))
      case "NE"=> Some((row - 1, col + 1))
      case "E" => Some((row, col + 1))
      case "SE" => Some((row + 1, col + 1))
      case "S" => Some((row + 1, col))
      case "SW" => Some((row + 1, col - 1))
      case "W" => Some((row, col - 1))
      case "NW" => Some((row - 1, col - 1))
    }) else k -> None}


  def availableFriendlies: Map[String, Option[Field]] = neighborMap filter {case(k, v) => definedAndSameTeam(v)}

  private def definedAndSameTeam(v: Option[Field]): Boolean =
    v.isDefined && v.get.isInstanceOf[OccupiedField] && v.get.team.equals(center.team)

  def availableEnemies: Map[String, Option[Field]] = neighborMap filter {case(k, v) => definedAndDifferentTeam(v)}

  private def definedAndDifferentTeam(v: Option[Field]): Boolean =
    v.isDefined && v.get.isInstanceOf[OccupiedField] && !v.get.team.equals(center.team)

  def valid: Boolean = atLeastOneReachableNeighbor || blockedFieldStandsAloneAllowed

  private def atLeastOneReachableNeighbor: Boolean =
    neighborMap.values.exists(n => n.isDefined && n.get.isInstanceOf[OccupiedField])

  private def blockedFieldStandsAloneAllowed: Boolean = neighborMap.values.count(n => n.isEmpty) > 5
}
