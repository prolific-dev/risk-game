package de.htwg.se.riskgame.model

import scala.io.AnsiColor.*

case class Desk(fields: Matrix[IField]) {
  def this(size: Int) = this(new Matrix[IField](size, Field("free")))

  val size: Int = fields.size

  def set(row: Int, col: Int, value: IField): Desk = copy(fields.replaceField(row, col, value))

  def neighbors(i: Int, j: Int): Neighbors = new Neighbors(i, j, fields)

  override def toString: String = {
    val sb = new StringBuilder("\n")
    val TOP_BOTTOM_LINE = ("+-" + ("-----" * size)) + "-+\n"
    val LEFT_WALL = "| "
    val RIGHT_WALL = " |\n"

    def consoleFieldString(field: IField): String = {
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

  def valid(): Boolean = {
    var valid = true
    for {
      i <- 0 until size
      j <- 0 until size; if (!neighbors(i, j).valid())
    } valid = false
    valid
  }

  def field(row: Int, col: Int): IField = fields.field(row, col)
}
