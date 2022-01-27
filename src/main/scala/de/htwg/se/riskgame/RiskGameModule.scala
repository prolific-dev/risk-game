package de.htwg.se.riskgame

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import de.htwg.se.riskgame.controller.controllerComponent.ControllerInterface
import de.htwg.se.riskgame.controller.controllerComponent.controllerBasicImpl.Controller
import de.htwg.se.riskgame.model.deskComponent.DeskInterface
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.Desk
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.deskCreatorComponent.DeskCreateStrategyTemplate
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.deskCreatorComponent.deskCreatorBasicImpl.DeskCreateContinentMapStrategy
import de.htwg.se.riskgame.model.fileIoComponent.FileIOInterface
//import de.htwg.se.riskgame.model.fileIoComponent.fileIoXmlImpl.FileIO
import de.htwg.se.riskgame.model.fileIoComponent.fileIoJsonImpl.FileIO

class RiskGameModule extends AbstractModule {
  val desk: DeskInterface = new DeskCreateContinentMapStrategy().createDesk()

  override def configure(): Unit = {
    bind(classOf[DeskInterface]).toInstance(desk)
    bind(classOf[FileIOInterface]).to(classOf[FileIO])
  }
}
