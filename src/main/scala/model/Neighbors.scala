package de.htwg.se.riskgame.model

case class Neighbors(row: Int, col: Int, fields: Matrix[IField]) {
  val map: Map[String, Option[IField]] = Map(
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

  def center(): IField = fields.field(row, col)

  def valid(): Boolean = (map.values.exists(n => n.isDefined && n.get.isInstanceOf[Field]))
    || (map.values.count(n => !n.isDefined) > 2)


}