package de.htwg.se.riskgame.model

import scala.io.AnsiColor.*
import scala.util.control.Breaks.break

case class Desk(fields: Matrix[IField], isColorized: Boolean) {
  def this(fields: Matrix[IField]) = this(fields, false)

  def this(size: Int) = this(new Matrix[IField](size, new Field("Free IField")))

  val size: Int = fields.size

  def set(row: Int, col: Int, value: IField): Desk = copy(fields.replaceField(row, col, value))

  override def toString: String = {

    def consoleFieldString(field: IField): String = {
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

  def setColorizedOn(): Desk = copy(fields, isColorized = true)

  def setColorizedOff(): Desk = copy(fields, isColorized = false)

  def field(row: Int, col: Int): IField = fields.field(row, col)

  def valid(): Boolean = {
    var valid = true
    for (i <- 0 until size; j <- 0 until size)
      if (neighbors(i, j).valid() == false) {
        valid = false
        break;
      }
    valid
  }

  def neighbors(i: Int, j: Int): Neighbors = new Neighbors(i, j, fields)
}