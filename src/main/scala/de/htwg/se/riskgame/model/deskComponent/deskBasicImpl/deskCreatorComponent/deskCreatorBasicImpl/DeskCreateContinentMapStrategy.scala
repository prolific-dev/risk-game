package de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.deskCreatorComponent.deskCreatorBasicImpl

import com.google.inject.{Guice, Inject, Injector}
import de.htwg.se.riskgame.RiskGameModule
import de.htwg.se.riskgame.model.*
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.*
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.deskCreatorComponent.DeskCreateStrategyTemplate
import de.htwg.se.riskgame.model.deskComponent.{DeskInterface, deskBasicImpl}
import de.htwg.se.riskgame.model.fileIoComponent.fileIoJsonImpl.FileIO
import de.htwg.se.riskgame.model.teamComponent.Team
import de.htwg.se.riskgame.model.troopComponent.Troop

class DeskCreateContinentMapStrategy() extends DeskCreateStrategyTemplate {
  val fileIO = new FileIO
  val fixSize: Int = 4

  def createDesk(): DeskInterface =
    var desk = prepare(fixSize)
    desk = fill(desk)
    postProcess(desk)

  override def prepare(size: Int): DeskInterface = deskBasicImpl.Desk(new Matrix[Field](fixSize, Field("x")), new DeskInfo())

  override def fill(desk: DeskInterface): DeskInterface = {
    fileIO.loadMap(desk)
  }
}
