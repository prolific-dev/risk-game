package de.htwg.se.riskgame.model

case class Neighbors(row: Int, col: Int, fields: Matrix[Field]) {
  val map: Map[String, Option[Field]] = Map(
    "N" -> {
      try {
        Some(fields.field(row - 1, col))
      } catch {
        case e: IndexOutOfBoundsException => None
      }
    },
    "S" -> {
      try {
        Some(fields.field(row + 1, col))
      } catch {
        case e: IndexOutOfBoundsException => None
      }
    },
    "W" -> {
      try {
        Some(fields.field(row, col - 1))
      } catch {
        case e: IndexOutOfBoundsException => None
      }
    },
    "E" -> {
      try {
        Some(fields.field(row, col + 1))
      } catch {
        case e: IndexOutOfBoundsException => None
      }
    })

  def center(): Field = fields.field(row, col)

  def valid(): Boolean = (map.values.exists(n => n.isDefined && n.get.isInstanceOf[OccupiedField]))
    || (map.values.count(n => !n.isDefined) > 2)


}