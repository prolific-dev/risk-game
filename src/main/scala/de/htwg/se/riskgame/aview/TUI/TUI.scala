package de.htwg.se.riskgame.aview.TUI

import de.htwg.se.riskgame.controller.Controller
import de.htwg.se.riskgame.model.*
import de.htwg.se.riskgame.util.Observer

import scala.io.StdIn.readLine

class TUI(controller: Controller) extends Observer {
  controller.add(this)
  val size = 3
  val teams = Seq(Team.BLUE, Team.RED)

  def run(): Unit =
    println(controller.deskToString)
    inputLoop()

  def inputLoop(): Unit =
    readLine match
      case "q" =>
      case input: String => processInputLine(input); inputLoop()

  def processInputLine(input: String): Unit =
    input match
      case "n" => controller.createEmptyDesk(size)
      case "r" => controller.createRandomDesk(size)
      case "w" => controller.createWorldMapDesk()
      case "s" =>
        controller.set(0, 0, Field("OccupiedField", Troop(3, Team.BLUE)))
        controller.set(0, 1, Field("OccupiedField", Troop(3, Team.BLUE)))
        controller.set(1, 0, Field("OccupiedField", Troop(3, Team.RED)))
      case "c f" => controller.chooseAndShowFriendlies(0, 0)
      case "c e" => controller.chooseAndShowEnemies(0, 0)
      case "r h" => controller.resetHighlight()
      case _ =>

  override def update: Unit =
    println("Player Turn: " + controller.currentPlayerTurnToString)
    println(controller.deskToString)
    println(controller.gameStatus.message)
}
