package de.htwg.se.riskgame.model.deskComponent

import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.*
import de.htwg.se.riskgame.model.teamComponent.Team

trait DeskInterface {

  def chooseFieldShowFriendlyNeighbors(neighbors: Neighbors): Desk

  def showHighlightNeighbors(available: Map[String, Option[Field]], neighbors: Neighbors): Desk

  def chooseFieldShowEnemyNeighbors(neighbors: Neighbors): Desk

  def resetHighlight: Desk

  def endTurn: Desk

  def teams: IndexedSeq[Team]

  def currentPlayerTurn: Team

  def set(row: Int, col: Int, value: Field): Desk

  def valid: Boolean

  def neighbors(i: Int, j: Int): Neighbors

  override def toString: String

  def field(row: Int, col: Int): Field
}
