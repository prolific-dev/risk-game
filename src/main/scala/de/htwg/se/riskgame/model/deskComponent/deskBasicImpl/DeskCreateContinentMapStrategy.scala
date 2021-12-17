package de.htwg.se.riskgame.model.deskComponent.deskBasicImpl

import de.htwg.se.riskgame.model.*
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.{Desk, DeskInfo, Field}
import de.htwg.se.riskgame.model.deskComponent.{DeskCreateStrategyTemplate, deskBasicImpl}
import de.htwg.se.riskgame.model.teamComponent.Team
import de.htwg.se.riskgame.model.troopComponent.Troop

class DeskCreateContinentMapStrategy extends DeskCreateStrategyTemplate {
  val fixSize: Int = 4

  def createDesk(): Desk =
    var desk = prepare(fixSize)
    desk = fill(desk)
    postProcess(desk)

  override def prepare(size: Int): Desk = deskBasicImpl.Desk(new Matrix[Field](fixSize, Field("x")), new DeskInfo())

  override def fill(desk: Desk): Desk = {
    var _desk = desk

    val continents = Vector[(Int, Int, Field)](
      (1, 0, Field("North America", Troop(1, Team.NO_TEAM))),
      (2, 0, Field("South America", troopComponent.Troop(1, Team.NO_TEAM))),
      (1, 1, Field("Europe", troopComponent.Troop(1, Team.NO_TEAM))),
      (2, 2, Field("Africa", troopComponent.Troop(1, Team.NO_TEAM))),
      (1, 3, Field("Asia", troopComponent.Troop(1, Team.NO_TEAM))),
      (2, 3, Field("Australia", troopComponent.Troop(1, Team.NO_TEAM)))
    )

    continents.foreach(continent => _desk = _desk.set(continent._1, continent._2, continent._3))
    _desk

  }
}
