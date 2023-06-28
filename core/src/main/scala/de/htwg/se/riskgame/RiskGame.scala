package de.htwg.se.riskgame

import de.htwg.se.riskgame.aview.tui.TUI
import de.htwg.se.riskgame.controller.controllerComponent.ControllerInterface
import de.htwg.se.riskgame.controller.controllerComponent.controllerBasicImpl.Controller
import de.htwg.se.riskgame.model.deskComponent.DeskInterface
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.Desk

object RiskGame:
  def main(args: Array[String]): Unit =
    val controller: ControllerInterface = new Controller(new Desk(0))
    val tui: TUI = new TUI(controller)
    tui.run()
