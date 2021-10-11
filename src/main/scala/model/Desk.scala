package de.htwg.se.riskgame.model

import scala.math.sqrt

case class Desk(fields: Matrix[Field]) {
  val size: Int = fields.size

  def this(size: Int) = this(new Matrix[Field](size, IllegalField()))

  def set(row: Int, col: Int, value: Field): Desk = copy(fields.replaceField(row, col, value))

  override def toString: String = {
    val lineseparator = ("+-" + ("-----" * size) + "-+\n")
    val line = ("| " + ("  x  " * size) + " |\n")
    var box = "\n" + (lineseparator + (line * size)) + lineseparator
    for {
      row <- 0 until size
      col <- 0 until size
    } box = box.replaceFirst("x", field(row, col).toString)
    box
  }

  def field(row: Int, col: Int): Field = fields.field(row, col)
}
