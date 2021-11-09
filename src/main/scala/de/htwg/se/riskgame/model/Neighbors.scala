package de.htwg.se.riskgame.model

import scala.util.{Failure, Success, Try}

case class Neighbors(row: Int, col: Int, fields: Matrix[Field]) {
  val map: Map[String, Option[Field]] = Map(
    "N" -> {
      Try(fields.field(row - 1, col)) match {
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
    "W" -> {
      Try(fields.field(row, col - 1)) match {
        case Success(field) => Some(field)
        case Failure(exception) => None
      }
    },
    "E" -> {
      Try(fields.field(row, col + 1)) match {
        case Success(field) => Some(field)
        case Failure(exception) => None
      }
    })

  def center(): Field = fields.field(row, col)

  def valid(): Boolean = (map.values.exists(n => n.isDefined && n.get.isInstanceOf[OccupiedField]))
    || (map.values.count(n => !n.isDefined) > 2)


}
