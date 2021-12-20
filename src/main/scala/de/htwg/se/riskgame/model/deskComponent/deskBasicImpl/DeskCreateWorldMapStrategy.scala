package de.htwg.se.riskgame.model.deskComponent.deskBasicImpl

import de.htwg.se.riskgame.model.*
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.{Desk, DeskInfo, Field}
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
    val defaultTroop = Troop(1, Team.NO_TEAM)

    val northAmerica = Vector[(Int, Int, Field)](
      (1, 3, Field("Greenland", defaultTroop)),
      (2, 0, Field("Alaska", defaultTroop)),
      (2, 1, Field("West Canada", defaultTroop)),
      (2, 2, Field("East Canada", defaultTroop)),
      (3, 0, Field("West America", defaultTroop)),
      (3, 1, Field("East America", defaultTroop)),
      (4, 0, Field("Mexico", defaultTroop)))

    val southAmerica = Vector[(Int, Int, Field)](
      (5, 1, Field("Colombia", defaultTroop)),
      (5, 2, Field("Venezuela", defaultTroop)),
      (5, 3, Field("Brazil", defaultTroop)),
      (6, 2, Field("Bolivia", defaultTroop)),
      (7, 2, Field("Argentina", defaultTroop)))

    val europe = Vector[(Int, Int, Field)](
      (2, 4, Field("Great Britain", defaultTroop)),
      (2, 5, Field("North Europe", defaultTroop)),
      (3, 4, Field("Iberian Island", defaultTroop)),
      (3, 5, Field("South Europe", defaultTroop)))

    val africa = Vector[(Int, Int, Field)](
      (4, 4, Field("West Africa", defaultTroop)),
      (4, 5, Field("East Africa", defaultTroop)),
      (5, 5, Field("Central Africa", defaultTroop)),
      (6, 5, Field("South Africa", defaultTroop)))

    val asia = Vector[(Int, Int, Field)](
      (2, 6, Field("West Russia", defaultTroop)),
      (2, 7, Field("Central Russia", defaultTroop)),
      (2, 8, Field("East Russia", defaultTroop)),
      (3, 6, Field("West Asia", defaultTroop)),
      (3, 7, Field("China", defaultTroop)),
      (4, 6, Field("Arabia", defaultTroop)),
      (4, 8, Field("South East Asia", defaultTroop)),
      (5, 8, Field("Indonesia", defaultTroop)),
      (5, 9, Field("Philippines", defaultTroop)))

    val australia = Vector[(Int, Int, Field)](
      (6, 9, Field("East Australia", defaultTroop)),
      (7, 8, Field("West Australia", defaultTroop)))

    val allContinents = Vector[Vector[(Int, Int, Field)]](northAmerica, southAmerica, europe, africa, asia, australia)

    allContinents.foreach(continent => continent.foreach(f => _desk = _desk.set(f._1, f._2, f._3)))
    _desk
  }

  override def postProcess(desk: Desk): Desk = if (desk.valid) desk else new Desk(3)

}
