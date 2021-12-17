package de.htwg.se.riskgame.model.deskComponent.deskBasicImpl

import de.htwg.se.riskgame.model.*
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.{Desk, DeskInfo, Field}
import de.htwg.se.riskgame.model.deskComponent.{DeskCreateStrategyTemplate, deskBasicImpl}
import de.htwg.se.riskgame.model.teamComponent.Team
import de.htwg.se.riskgame.model.troopComponent.Troop

class DeskCreateWorldMapStrategy extends DeskCreateStrategyTemplate {
  val fixSize: Int = 10

  def createDesk(): Desk =
    var desk = prepare(fixSize)
    desk = fill(desk)
    postProcess(desk)

  override def prepare(size: Int): Desk = deskBasicImpl.Desk(new Matrix[Field](10, Field("x")), new DeskInfo())

  override def fill(desk: Desk): Desk = {
    var _desk = desk

    val northAmerica = Vector[(Int, Int, Field)](
      (1, 3, Field("Greenland", Troop(1, Team.NO_TEAM))),
      (2, 0, Field("Alaska", troopComponent.Troop(1, Team.NO_TEAM))),
      (2, 1, Field("West Canada", troopComponent.Troop(1, Team.NO_TEAM))),
      (2, 2, Field("East Canada", troopComponent.Troop(1, Team.NO_TEAM))),
      (3, 0, Field("West America", troopComponent.Troop(1, Team.NO_TEAM))),
      (3, 1, Field("East America", troopComponent.Troop(1, Team.NO_TEAM))),
      (4, 0, Field("Mexico", troopComponent.Troop(1, Team.NO_TEAM)))
    )
    val southAmerica = Vector[(Int, Int, Field)](
      (5, 1, Field("Colombia", troopComponent.Troop(1, Team.NO_TEAM))),
      (5, 2, Field("Venezuela", troopComponent.Troop(1, Team.NO_TEAM))),
      (5, 3, Field("Brazil", troopComponent.Troop(1, Team.NO_TEAM))),
      (6, 2, Field("Bolivia", troopComponent.Troop(1, Team.NO_TEAM))),
      (7, 2, Field("Argentina", troopComponent.Troop(1, Team.NO_TEAM)))
    )
    val europe = Vector[(Int, Int, Field)](
      (2, 4, Field("Great Britain", troopComponent.Troop(1, Team.NO_TEAM))),
      (2, 5, Field("North Europe", troopComponent.Troop(1, Team.NO_TEAM))),
      (3, 4, Field("Iberian Island", troopComponent.Troop(1, Team.NO_TEAM))),
      (3, 5, Field("South Europe", troopComponent.Troop(1, Team.NO_TEAM)))
    )
    val africa = Vector[(Int, Int, Field)](
      (4, 4, Field("West Africa", troopComponent.Troop(1, Team.NO_TEAM))),
      (4, 5, Field("East Africa", troopComponent.Troop(1, Team.NO_TEAM))),
      (5, 5, Field("Central Africa", troopComponent.Troop(1, Team.NO_TEAM))),
      (6, 5, Field("South Africa", troopComponent.Troop(1, Team.NO_TEAM)))
    )
    val asia = Vector[(Int, Int, Field)](
      (2, 6, Field("West Russia", troopComponent.Troop(1, Team.NO_TEAM))),
      (2, 7, Field("Central Russia", troopComponent.Troop(1, Team.NO_TEAM))),
      (2, 8, Field("East Russia", troopComponent.Troop(1, Team.NO_TEAM))),
      (3, 6, Field("West Asia", troopComponent.Troop(1, Team.NO_TEAM))),
      (3, 7, Field("China", troopComponent.Troop(1, Team.NO_TEAM))),
      (4, 6, Field("Arabia", troopComponent.Troop(1, Team.NO_TEAM))),
      (4, 8, Field("South East Asia", troopComponent.Troop(1, Team.NO_TEAM))),
      (5, 8, Field("Indonesia", troopComponent.Troop(1, Team.NO_TEAM))),
      (5, 9, Field("Philippines", troopComponent.Troop(1, Team.NO_TEAM)))
    )
    val australia = Vector[(Int, Int, Field)](
      (6, 9, Field("East Australia", troopComponent.Troop(1, Team.NO_TEAM))),
      (7, 8, Field("West Australia", troopComponent.Troop(1, Team.NO_TEAM)))
    )
    val allContinents = Vector[Vector[(Int, Int, Field)]](northAmerica, southAmerica, europe, africa, asia, australia)
    allContinents.foreach(continent => continent.foreach(f => _desk = _desk.set(f._1, f._2, f._3)))
    _desk
  }

  override def postProcess(desk: Desk): Desk = if (desk.valid) desk else new Desk(3)

}
