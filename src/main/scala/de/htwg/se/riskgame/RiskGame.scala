package de.htwg.se.riskgame

import de.htwg.se.riskgame.aview.Tui
import de.htwg.se.riskgame.controller.Controller
import de.htwg.se.riskgame.model.{Desk, Field, Player}

import scala.io.StdIn.readLine

object RiskGame {
  val controller = new Controller(new Desk(3))
  val tui = new Tui(controller)
  controller.notifyObserver

  @main def run: Unit = {
    var input: String = ""
    var playerName: String = ""

    println("Welcome to Risk Game!")
    println("\nWhat's your name?")
    playerName = readLine()
    println("\nNice to see you on the battlefield, " + playerName + "!")

     while
       input != "q"
    do
      input = readLine()
       tui.processInputLine(input)

  }
}
