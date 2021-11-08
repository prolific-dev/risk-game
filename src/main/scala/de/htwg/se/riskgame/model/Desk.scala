package de.htwg.se.riskgame.model

import scala.io.AnsiColor.*

case class Desk(fields: Matrix[Field], isColorized: Boolean) {
  def this(fields: Matrix[Field]) = this(fields, false)

  def this(size: Int) = this(new Matrix[Field](size, new OccupiedField()))

  val size: Int = fields.size

  def set(row: Int, col: Int, value: Field): Desk = copy(fields.replaceField(row, col, value))

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

  def neighbors(i: Int, j: Int): Neighbors = new Neighbors(i, j, fields)

  def valid(): Boolean = {
    var valid = true
    var i = 0
    while (valid && i < size) {
      for (j <- 0 until size) {
        if (!neighbors(i, j).valid()) {
          valid = false
        }
      }
      i += 1
    }
    valid
  }

  def field(row: Int, col: Int): Field = fields.field(row, col)
}
