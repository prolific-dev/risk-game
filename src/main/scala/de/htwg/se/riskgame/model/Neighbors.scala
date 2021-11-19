package de.htwg.se.riskgame.model

import scala.util.{Failure, Success, Try}

case class Neighbors(row: Int, col: Int, fields: Matrix[IField]) {
  val neighborMap: Map[String, Option[IField]] = Map(
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

  def center(): IField = fields.field(row, col)

  def valid(): Boolean = (neighborMap.values.exists(n => n.isDefined && n.get.isInstanceOf[OccupiedField]))
    || (neighborMap.values.count(n => !n.isDefined) > 5)


}
