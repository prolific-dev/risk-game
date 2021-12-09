package de.htwg.se.riskgame.model

import scala.io.AnsiColor.*
import scala.language.postfixOps

case class Desk(fields: Matrix[Field], info: DeskInfo) {
  def this(size: Int, teams: IndexedSeq[Team]) = this(new Matrix[Field](size, Field("free")), DeskInfo(teams, 0))

  def this(size: Int) = this(size, IndexedSeq[Team](Team.BLUE, Team.RED))

  val size: Int = fields.size

  def chooseFieldShowAvailableFriendlies(neighbors: Neighbors): Desk =
    showFieldHighlight(neighbors.availableFriendlies, neighbors)

  def chooseFieldShowAvailableEnemies(neighbors: Neighbors): Desk =
    showFieldHighlight(neighbors.availableEnemies, neighbors)

  def showFieldHighlight(available: Map[String, Option[Field]], neighbors: Neighbors): Desk = {
    var _desk = copy()
    available foreach {case(k, v) =>
      val x = neighbors.neighborCoordinates(k).get._1
      val y = neighbors.neighborCoordinates(k).get._2
      _desk = _desk.copy(fields = _desk.fields.replaceField(x, y, v.get.highlightOn))}
    _desk
  }

  def resetHighlight: Desk =
    var _desk = copy()
    (0 until size).foreach(i => (0 until size).foreach(j => if (field(i, j).isInstanceOf[OccupiedField])
      _desk = _desk.copy(fields = _desk.fields.replaceField(i, j, field(i, j).highlightOff))))
    _desk


  def endTurn: Desk = copy(fields, info.endTurn)

  def teams: IndexedSeq[Team] = info.teams

  def currentPlayerTurn: Team = info.currentPlayerTurn

  def set(row: Int, col: Int, value: Field): Desk = copy(fields.replaceField(row, col, value))

  def neighbors(i: Int, j: Int): Neighbors = Neighbors(i, j, fields)

  def valid: Boolean = !(0 until size).flatten(i => (0 until size).map(j => neighbors(i, j).valid)).contains(false)

  override def toString: String =
    val sb = new StringBuilder(("\n+-" + ("-----" * size)) + "-+\n")
    (0 until size)
      .foreach(i =>
        sb ++= "| ";
        (0 until size)
          .foreach(j =>
            sb ++= "  " + field(i, j).toString + "  ");
        sb ++= " |\n")
    sb ++= ("+-" + ("-----" * size)) + "-+\n"
    sb.toString()

  def field(row: Int, col: Int): Field = fields.field(row, col)
}
