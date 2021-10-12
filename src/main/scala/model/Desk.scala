package de.htwg.se.riskgame.model

import scala.math.sqrt

case class Desk(fields: Matrix[Field]) {
  val size: Int = fields.size

  def this(size: Int) = this(new Matrix[Field](size, IllegalField()))

  def set(row: Int, col: Int, value: Field): Desk = copy(fields.replaceField(row, col, value))

  override def toString: String = {
    val TOP_BOTTOM_LINE = ("+-" + ("-----" * size)) + "-+\n"
    val LEFT_WALL = "| "
    val RIGHT_WALL = " |\n"

    val sb = new StringBuilder("\n")

    sb.append(TOP_BOTTOM_LINE)
    for (i <- 0 until size) {
      sb.append(LEFT_WALL)
      for (j <- 0 until size) {
        sb.append("  " + field(i, j).toString + "  ")
      }
      sb.append(RIGHT_WALL)
    }
    sb.append(TOP_BOTTOM_LINE)
    sb.toString()
  }

  def field(row: Int, col: Int): Field = fields.field(row, col)
}
