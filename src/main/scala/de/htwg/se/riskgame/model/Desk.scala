package de.htwg.se.riskgame.model

import scala.io.AnsiColor.*

case class Desk(fields: Matrix[Field], info: DeskInfo) {
  def this(size: Int, teams: IndexedSeq[Team]) = this(new Matrix[Field](size, Field("free")), new DeskInfo(teams, 0))

  def this(size: Int) = this(size, IndexedSeq[Team](Team.BLUE, Team.RED))

  val size: Int = fields.size

  def chooseFieldAndShowAvailableEnemies(i: Int, j: Int): Desk = {
    var updated = this
    val available = neighbors(i, j).neighborMap
      .map(field => if (field._2.isDefined && (field._2.get.team() != info.currentPlayerTurn)) field else None)
      .toIndexedSeq


    copy(fields, info)
  }

  def endTurn: Desk = copy(fields, info.endTurn)

  def teams: IndexedSeq[Team] = info.teams

  def currentPlayerTurn: Team = info.currentPlayerTurn

  def set(row: Int, col: Int, value: Field): Desk = copy(fields.replaceField(row, col, value))

  def neighbors(i: Int, j: Int): Neighbors = new Neighbors(i, j, fields)

  def valid(): Boolean = !(0 until size).flatten(i => (0 until size).map(j => neighbors(i, j).valid)).contains(false)

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
