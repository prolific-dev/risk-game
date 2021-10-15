package de.htwg.se.riskgame

import aview.Tui
import model.{Desk, LegalField, Player}

import scala.io.StdIn.readLine

object RiskGame {
  val tui = new Tui
  var desk = new Desk(0)

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
      println("Your Desk : " + desk.toString)
      input = readLine()
      desk = tui.processInputLine(input, desk)

  }
}
