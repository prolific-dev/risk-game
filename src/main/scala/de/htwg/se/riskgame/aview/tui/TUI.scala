package de.htwg.se.riskgame.aview.tui

import de.htwg.se.riskgame.controller.controllerComponent.ControllerInterface
import de.htwg.se.riskgame.model.*
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.Field
import de.htwg.se.riskgame.model.teamComponent.Team
import de.htwg.se.riskgame.model.troopComponent.Troop
import de.htwg.se.riskgame.util.Observer

import scala.io.StdIn.readLine

class TUI(controller: ControllerInterface) extends Observer {
  controller.add(this)

  def run(): Unit =
    println(controller.deskToString)
    inputLoop()

  def inputLoop(): Unit =
    readLine match {
      case "q" =>
      case input: String => processInputLine(input); inputLoop()
    }

  def processInputLine(input: String): Unit =
    input match {
      case "n" => controller.createEmptyDesk(3)
      case "r" => controller.createRandomDesk(3)
      case "w" => controller.createWorldMapDesk()
      case "c" => controller.createContinentMapDesk()
      case "s" => controller.set(1, 0, Field("North America", Troop(3, Team.BLUE)))
      case "c f" => controller.chooseFieldShowFriendlies(1, 0)
      case "c e" => controller.chooseFieldShowEnemies(1, 0)
      case "r h" => controller.resetHighlight()
      case _ =>
    }

  override def update: Unit =
    println("Player Turn: " + controller.currentPlayerTurnToString)
    println(controller.deskToString)
    println(controller.gameStatus.message)
}
