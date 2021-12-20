package de.htwg.se.riskgame.model.deskComponent

import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.*
import de.htwg.se.riskgame.model.teamComponent.Team

trait DeskInterface {
  def fields: Matrix[Field]

  def info: DeskInfo

  def size: Int

  def chooseFieldShowFriendlyNeighbors(neighbors: Neighbors): DeskInterface

  def showHighlightNeighbors(available: Map[String, Option[Field]], neighbors: Neighbors): DeskInterface

  def chooseFieldShowEnemyNeighbors(neighbors: Neighbors): DeskInterface

  def resetHighlight: DeskInterface

  def endTurn: DeskInterface

  def teams: IndexedSeq[Team]

  def currentPlayerTurn: Team

  def set(row: Int, col: Int, value: Field): DeskInterface

  def valid: Boolean

  def neighbors(i: Int, j: Int): Neighbors

  override def toString: String

  def field(row: Int, col: Int): Field
}
