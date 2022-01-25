package de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.deskCreatorComponent.deskCreatorBasicImpl

import com.google.inject.Inject
import de.htwg.se.riskgame.model.*
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.*
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.deskCreatorComponent.DeskCreateStrategyTemplate
import de.htwg.se.riskgame.model.deskComponent.{DeskInterface, deskBasicImpl}
import de.htwg.se.riskgame.model.teamComponent.Team
import de.htwg.se.riskgame.model.troopComponent.Troop

class DeskCreateContinentMapStrategy() extends DeskCreateStrategyTemplate {
  val fixSize: Int = 4

  def createDesk(): DeskInterface =
    var desk = prepare(fixSize)
    desk = fill(desk)
    postProcess(desk)

  override def prepare(size: Int): DeskInterface = deskBasicImpl.Desk(new Matrix[Field](fixSize, Field("x")), new DeskInfo())

  override def fill(desk: DeskInterface): DeskInterface = {
    var _desk = desk
    val defaultTroop = Troop(1, Team.NO_TEAM)

    val continents = Vector[(Int, Int, Field)](
      (1, 0, Field("North America", defaultTroop)),
      (2, 0, Field("South America", defaultTroop)),
      (1, 1, Field("Europe", defaultTroop)),
      (2, 2, Field("Africa", defaultTroop)),
      (1, 3, Field("Asia", defaultTroop)),
      (2, 3, Field("Australia", defaultTroop)))

    continents.foreach(continent => _desk = _desk.set(continent._1, continent._2, continent._3))
    _desk
  }
}
