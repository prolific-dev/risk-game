package de.htwg.se.riskgame.model

import scala.io.AnsiColor.*

case class Desk(fields: Matrix[Field]) {
  def this(size: Int) = this(new Matrix[Field](size, new OccupiedField()))

  val size: Int = fields.size

  def set(row: Int, col: Int, value: Field): Desk = copy(fields.replaceField(row, col, value))

  def neighbors(i: Int, j: Int): Neighbors = new Neighbors(i, j, fields)

  def field(row: Int, col: Int): Field = fields.field(row, col)

  def valid(): Boolean = {
    var valid = true
    for {
      i <- 0 until size
      j <- 0 until size; if (!neighbors(i, j).valid())
    } valid = false
    valid
  }

  override def toString: String = {
    val sb = new StringBuilder("\n")
    val TOP_BOTTOM_LINE = ("+-" + ("-----" * size)) + "-+\n"
    val LEFT_WALL = "| "
    val RIGHT_WALL = " |\n"

    def consoleFieldString(field: Field): String = {
      field.team() match
        case Team.NO_TEAM => field.toString
        case _ => field.team().getAnsi + field.toString + RESET
    }

    sb ++= TOP_BOTTOM_LINE
    for (i <- 0 until size) {
      sb ++= LEFT_WALL
      for (j <- 0 until size) {
        sb ++= "  " + consoleFieldString(field(i, j)) + "  "
      }
      sb ++= RIGHT_WALL
    }
    sb ++= TOP_BOTTOM_LINE
    sb.toString()
  }
}
