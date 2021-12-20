package de.htwg.se.riskgame

import de.htwg.se.riskgame.RiskGame.controller
import de.htwg.se.riskgame.aview.gui.GUI
import de.htwg.se.riskgame.aview.tui.TUI
import de.htwg.se.riskgame.controller.controllerComponent.controllerBasicImpl.Controller
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.{Desk, DeskCreateContinentMapStrategy}
import scalafx.application.Platform.runLater
import scalafx.application.{JFXApp3, Platform}

import scala.io.StdIn.readLine


object RiskGame {
  val desk: Desk = new DeskCreateContinentMapStrategy().createDesk()
  val controller: Controller = new Controller(desk)
}

object GUIStarter extends JFXApp3 {
  val tui = new TUI(RiskGame.controller)
  val gui = new GUI(RiskGame.controller)

  override def start(): Unit = gui.start()

}

object TUIRunner extends App {
  val tui = new TUI(RiskGame.controller)
  tui.run()
}


