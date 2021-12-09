package de.htwg.se.riskgame.model

case class DeskInfo(teams: IndexedSeq[Team], playerTurn: Int) {
  def this() = this(IndexedSeq[Team](Team.BLUE, Team.RED), 0)

  val lastTeam: Int = teams.size - 1

  def endTurn: DeskInfo = if (playerTurn == lastTeam) copy(teams, 0) else copy(teams, playerTurn + 1)

  def currentPlayerTurn: Team = teams(playerTurn)
}
