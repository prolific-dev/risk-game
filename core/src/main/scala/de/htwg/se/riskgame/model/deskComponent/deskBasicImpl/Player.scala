package de.htwg.se.riskgame.model.deskComponent.deskBasicImpl

case class Player(id: Int, name: String, team: Team):
    def this(id: Int) = this(id, s"P$id", id match
        case 1 => Team.Blue
        case 2 => Team.Red
        case 3 => Team.Green
        case 4 => Team.Yellow
        case _ => Team.NoTeam)
