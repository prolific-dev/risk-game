package de.htwg.se.riskgame.model.deskComponent.deskBasicImpl

import de.htwg.se.riskgame.model.teamComponent.Team

case class DeskInfo(teams: IndexedSeq[Team], playerTurn: Int, chosenField: Option[Field]) {
  val lastTeam: Int = teams.size - 1

  def this() = this(IndexedSeq[Team](Team.BLUE, Team.RED), 0, None)

  def setChosenField(field: Option[Field]): DeskInfo = copy(chosenField = field)

  def endTurn: DeskInfo = if (playerTurn == lastTeam) copy(teams, 0) else copy(teams, playerTurn + 1)

  def currentPlayerTurn: Team = teams(playerTurn)
}
