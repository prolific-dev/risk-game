package de.htwg.se.riskgame

import de.htwg.se.riskgame.aview.GUI.GUI
import de.htwg.se.riskgame.aview.TUI.TUI
import de.htwg.se.riskgame.controller.Controller
import de.htwg.se.riskgame.model.{Desk, OccupiedField, Player}
import scalafx.application.JFXApp3

import scala.io.StdIn.readLine

@main def run(): Unit =
  val desk = new Desk(3)
  val controller = new Controller(desk)

  new Thread {
    override def run(): Unit = GUI(controller).main(Array())
  }.start()
  new Thread {
    override def run(): Unit = TUI(controller).run()
  }.start()


object Tui extends App {
  val desk = new Desk(3)
  val controller = new Controller(desk)
  val tui = TUI(controller)
  tui.run()
}

object Gui extends JFXApp3 {
  val desk = new Desk(3)
  val controller = new Controller(desk)

  override def start(): Unit = GUI(controller).start()
}
