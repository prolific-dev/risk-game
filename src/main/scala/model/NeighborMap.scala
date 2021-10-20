package de.htwg.se.riskgame.model

case class NeighborMap(row: Int, col: Int, fields: Matrix[IField]) {
  def mapNeighbors(): Map[String, Option[IField]] = {
    val map = Map(
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
    map
  }

  def center(): IField = fields.field(row, col)
}
