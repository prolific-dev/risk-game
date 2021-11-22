package de.htwg.se.riskgame.model

import scala.io.AnsiColor.*

case class Desk(fields: Matrix[Field]) {
  def this(size: Int) = this(new Matrix[Field](size, Field("free")))

  val size: Int = fields.size

  def set(row: Int, col: Int, value: Field): Desk = copy(fields.replaceField(row, col, value))

  def neighbors(i: Int, j: Int): Neighbors = new Neighbors(i, j, fields)

  override def toString: String = {
    def consoleFieldString(field: Field): String = {
      field.team() match
        case Team.NO_TEAM => field.toString
        case _ => field.team().getAnsi + field.toString + RESET
    }

    val sb = new StringBuilder(("\n+-" + ("-----" * size)) + "-+\n")
    (0 until size).foreach(i => {
      sb ++= "| "; (0 until size).foreach(j => sb ++= "  " + consoleFieldString(field(i, j)) + "  "); sb ++= " |\n"
    })
    sb ++= ("+-" + ("-----" * size)) + "-+\n"
    sb.toString()
  }

  def valid(): Boolean = {
    var valid = true
    (0 until size).foreach(i => (0 until size).foreach(j => if (!neighbors(i, j).valid()) {
      valid = false
    }))
    valid
  }

  def field(row: Int, col: Int): Field = fields.field(row, col)
}
