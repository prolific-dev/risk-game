package de.htwg.se.riskgame.model

import scala.math.sqrt

case class Desk(fields: Matrix[Field]) {
  val size: Int = fields.size

  def this(size: Int) = this(new Matrix[Field](size, IllegalField()))

  def set(row: Int, col: Int, value: Field): Desk = copy(fields.replaceField(row, col, value))

  override def toString: String = {
    val sb = new StringBuilder("\n")
    val topbottomLine = ("+-" + ("-----" * size)) + "-+\n"
    val leftWall = "| "
    val rightWall = " |\n"

    sb.append(topbottomLine)
    for (i <- 0 until size) {
      sb.append(leftWall)
      for (j <- 0 until size) {
        sb.append("  " + field(i, j).toString + "  ")
      }
      sb.append(rightWall)
    }
    sb.append(topbottomLine)
    sb.toString()
  }

  def field(row: Int, col: Int): Field = fields.field(row, col)
}
