package de.htwg.se.riskgame.model.deskComponent.deskBasicImpl

import com.google.inject.Inject
import com.google.inject.name.Named
import de.htwg.se.riskgame.model.*
import de.htwg.se.riskgame.model.deskComponent.{deskBasicImpl, DeskInterface}
import de.htwg.se.riskgame.model.teamComponent.Team

case class Desk @Inject(fields: Matrix[Field], info: DeskInfo) extends DeskInterface {
  val size: Int = fields.size

  def this(size: Int, teams: IndexedSeq[Team]) = this(new Matrix[Field](size, Field("free")), DeskInfo(teams, 0, None))

  def this(size: Int) = this(size, IndexedSeq[Team](Team.BLUE, Team.RED))

  def chooseField(row: Int, col: Int): Desk = copy(info = info.setChosenField(Some(field(row, col))))

  def field(row: Int, col: Int): Field = fields.field(row, col)

  def chooseFieldShowFriendlyNeighbors(neighbors: Neighbors): Desk =
    showHighlightNeighbors(neighbors.availableFriendlies, neighbors)

  def chooseFieldShowEnemyNeighbors(neighbors: Neighbors): Desk =
    showHighlightNeighbors(neighbors.availableEnemies, neighbors)

  def resetHighlight: Desk =
    var _desk = copy()
    (0 until size).foreach(i =>
      (0 until size).foreach(j =>
        if (field(i, j).isInstanceOf[OccupiedField])
          _desk = _desk.copy(fields = _desk.fields.replaceField(i, j, field(i, j).highlightOff))
      )
    )
    _desk

  def showHighlightNeighbors(available: Map[String, Option[Field]], neighbors: Neighbors): Desk = {
    var _desk = copy(info = info.setChosenField(Some(neighbors.center)))
    available foreach { case (k, v) =>
      val x = neighbors.neighborCoordinates(k).get._1
      val y = neighbors.neighborCoordinates(k).get._2
      _desk = _desk.copy(fields = _desk.fields.replaceField(x, y, v.get.highlightOn))
    }
    _desk
  }

  def resetChosenField: Desk = copy(info = info.setChosenField(None))

  def endTurn: Desk = copy(fields, info.endTurn)

  def teams: IndexedSeq[Team] = info.teams

  def currentPlayerTurn: Team = info.currentPlayerTurn

  def set(row: Int, col: Int, value: Field): Desk = copy(fields.replaceField(row, col, value))

  def valid: Boolean = !(0 until size).flatten(i => (0 until size).map(j => neighbors(i, j).valid)).contains(false)

  def neighbors(i: Int, j: Int): Neighbors = Neighbors(i, j, fields)

  override def toString: String =
    val sb = new StringBuilder(("\n+-" + ("-----" * size)) + "-+\n")
    (0 until size)
      .foreach(i =>
        sb ++= "| ";
        (0 until size)
          .foreach(j => sb ++= "  " + field(i, j).toString + "  ");
        sb ++= " |\n"
      )
    sb ++= ("+-" + ("-----" * size)) + "-+\n"
    sb.toString()
}
