package de.htwg.se.riskgame.model

import scala.io.AnsiColor.*

case class Desk(fields: Matrix[Field], teams: Seq[Team]) {
  val playersTurn = 0

  def this(size: Int, teams: Seq[Team]) = this(new Matrix[Field](size, Field("free")), teams)

  val size: Int = fields.size

  def this(size: Int) = this(size, Seq[Team](Team.BLUE, Team.RED))

  def set(row: Int, col: Int, value: Field): Desk = copy(fields.replaceField(row, col, value))

  def neighbors(i: Int, j: Int): Neighbors = new Neighbors(i, j, fields)

  def valid(): Boolean = !(0 until size).flatten(i => (0 until size).map(j => neighbors(i, j).valid())).contains(false)

  override def toString: String = {
    val sb = new StringBuilder(("\n+-" + ("-----" * size)) + "-+\n")
    (0 until size)
      .foreach(i => {
        sb ++= "| ";
        (0 until size)
          .foreach(j =>
            sb ++= "  " + field(i, j).toString + "  ");
        sb ++= " |\n"
      })
    sb ++= ("+-" + ("-----" * size)) + "-+\n"
    sb.toString()
  }

  def field(row: Int, col: Int): Field = fields.field(row, col)
}
