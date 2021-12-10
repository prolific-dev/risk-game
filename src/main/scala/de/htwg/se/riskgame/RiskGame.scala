package de.htwg.se.riskgame

import de.htwg.se.riskgame.aview.GUI.GUI
import de.htwg.se.riskgame.aview.TUI.TUI
import de.htwg.se.riskgame.controller.Controller
import de.htwg.se.riskgame.model.{Desk, OccupiedField, Player}
import scalafx.application.JFXApp3

import scala.io.StdIn.readLine

object RiskGame {
  val desk = new Desk(3)
  val controller = new Controller(desk)
  val GUI = new GUI(controller)
  val TUI = new TUI(controller)

  def startGUI(): Unit = GUI.start()

  def runTUI(): Unit = TUI.run()

}

object RiskGameGUI extends JFXApp3 {
  override def start(): Unit = RiskGame.startGUI()
}

object RiskGameTUI extends App {
  RiskGame.runTUI()
}


