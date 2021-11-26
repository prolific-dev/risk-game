package de.htwg.se.riskgame

import de.htwg.se.riskgame.aview.TUI
import de.htwg.se.riskgame.controller.Controller
import de.htwg.se.riskgame.model.{Desk, OccupiedField, Player}

import scala.io.StdIn.readLine


@main def run: Unit = {
  println("Welcome to Risk Game!")
  val desk = new Desk(3)
  val controller = new Controller(desk)
  val tui = new TUI(controller)
  tui.run()
}
