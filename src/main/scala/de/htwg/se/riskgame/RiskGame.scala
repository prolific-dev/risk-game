package de.htwg.se.riskgame

import de.htwg.se.riskgame.aview.{GUI, TUI}
import de.htwg.se.riskgame.controller.Controller
import de.htwg.se.riskgame.model.{Desk, OccupiedField, Player}

import scala.io.StdIn.readLine


@main def run(): Unit = {
  println("Welcome to Risk Game!")
  val desk = new Desk(3)
  val controller = new Controller(desk)
  val gui = new GUI(controller)
  val tui = new TUI(controller)
  gui.run()
  tui.run()


}
