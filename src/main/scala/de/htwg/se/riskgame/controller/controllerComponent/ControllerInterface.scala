package de.htwg.se.riskgame.controller.controllerComponent

import de.htwg.se.riskgame.controller.GameStatus
import de.htwg.se.riskgame.controller.controllerComponent.controllerBasicImpl.SetFieldCommand
import de.htwg.se.riskgame.model.deskComponent.DeskInterface
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.*
import de.htwg.se.riskgame.model.teamComponent.Team
import de.htwg.se.riskgame.util.{Observable, UndoManager}

trait ControllerInterface extends Observable {
  def gameStatus: GameStatus

  def undoManager: UndoManager

  def desk: DeskInterface

  def createEmptyDesk(size: Int): Unit

  def createRandomDesk(size: Int): Unit

  def createContinentMapDesk(): Unit

  //  def createWorldMapDesk(): Unit

  def set(row: Int, col: Int, field: Field): Unit

  def save: Unit

  def load: Unit

  def exit: Unit

  def chooseFieldShowFriendlies(row: Int, col: Int): Unit

  def chooseFieldShowEnemies(row: Int, col: Int): Unit

  def chooseField(row: Int, col: Int): Unit

  def resetChosenField: Unit

  def resetHighlight: Unit

  def undo: Unit

  def redo: Unit

  def endTurn: Unit

  def getCurrentPlayerTurn: Team

  def currentPlayerTurnToString: String

  def deskToString: String
}
