package de.htwg.se.riskgame.model

import scala.io.AnsiColor.*


case class Desk(fields: Matrix[Field], isColorized: Boolean) {
  def this(fields: Matrix[Field]) = this(fields, false)

  def this(size: Int) = this(new Matrix[Field](size, new LegalField("Free Field")))

  val size: Int = fields.size

  def set(row: Int, col: Int, value: Field): Desk = copy(fields.replaceField(row, col, value))

  def field(row: Int, col: Int): Field = fields.field(row, col)

  def setColorizedOn(): Desk = copy(fields, isColorized = true)

  def setColorizedOff(): Desk = copy(fields, isColorized = false)

  override def toString: String = {

    def consoleFieldString(field: Field): String = {
      isColorized match
        case true => field.team().getAnsi + field.toString + RESET
        case false => field.toString

    }

    val TOP_BOTTOM_LINE = ("+-" + ("-----" * size)) + "-+\n"
    val LEFT_WALL = "| "
    val RIGHT_WALL = " |\n"

    val sb = new StringBuilder("\n")

    sb.append(TOP_BOTTOM_LINE)
    for (i <- 0 until size) {
      sb.append(LEFT_WALL)
      for (j <- 0 until size) {
        sb.append("  " + consoleFieldString(field(i, j)) + "  ")
      }
      sb.append(RIGHT_WALL)
    }
    sb.append(TOP_BOTTOM_LINE)
    sb.toString()
  }

  def neighbors(i: Int, j: Int): Map[String, Option[Field]] = {
    val map = Map(
      "N" -> {
        try {
          Some(fields.field(i + 1, j))
        } catch {
          case e: IndexOutOfBoundsException => None
        }
      },
      "S" -> {
        try {
          Some(fields.field(i - 1, j))
        } catch {
          case e: IndexOutOfBoundsException => None
        }
      },
      "W" -> {
        try {
          Some(fields.field(i, j - 1))
        } catch {
          case e: IndexOutOfBoundsException => None
        }
      },
      "E" -> {
        try {
          Some(fields.field(i, j + 1))
        } catch {
          case e: IndexOutOfBoundsException => None
        }
      })
    map
  }
}