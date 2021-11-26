package de.htwg.se.riskgame.model

case class DeskInfo(teams: IndexedSeq[Team], playerTurn: Int) {
  def this() = this(IndexedSeq[Team](Team.BLUE, Team.RED), 0)

  def endTurn: DeskInfo = if (playerTurn < teams.size - 1) copy(teams, playerTurn + 1) else copy(teams, 0)

  def currentPlayerTurn: Team = teams(playerTurn)
}
