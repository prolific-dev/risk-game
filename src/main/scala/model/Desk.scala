package de.htwg.se.riskgame.model

import scala.io.AnsiColor.*


case class Desk(fields: Matrix[Field], isColorized: Boolean) {
  def this(fields: Matrix[Field]) = this(fields, false)

  def this(size: Int) = this(new Matrix[Field](size, IllegalField()))

  val size: Int = fields.size

  def set(row: Int, col: Int, value: Field): Desk = copy(fields.replaceField(row, col, value))

  def field(row: Int, col: Int): Field = fields.field(row, col)

  def setColorizedOn(): Desk = copy(fields, isColorized = true)

  def setColorizedOff(): Desk = copy(fields, isColorized = false)

  override def toString: String = {
    val TOP_BOTTOM_LINE = ("+-" + ("-----" * size)) + "-+\n"
    val LEFT_WALL = "| "
    val RIGHT_WALL = " |\n"

    val sb = new StringBuilder("\n")

    sb.append(TOP_BOTTOM_LINE)
    for (i <- 0 until size) {
      sb.append(LEFT_WALL)
      for (j <- 0 until size) {
        val string = consoleFieldString(field(i, j))
        sb.append("  " + string + "  ")
      }
      sb.append(RIGHT_WALL)
    }
    sb.append(TOP_BOTTOM_LINE)
    sb.toString()
  }

  def consoleFieldString(field: Field): String = {
    if (isColorized)
      field.team().getAnsi + field.toString + RESET
    else
      field.toString
  }
}
