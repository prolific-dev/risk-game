package de.htwg.se.riskgame.controller

import de.htwg.se.riskgame.model.*
import de.htwg.se.riskgame.util.{Observable, UndoManager}

class Controller(var desk: Desk) extends Observable {
  var gameStatus: GameStatus = GameStatus.IDLE
  private val undoManager = new UndoManager

  def createEmptyDesk(size: Int): Unit =
    desk = new Desk(size)
    gameStatus = GameStatus.EMPTY
    notifyObserver

  def createRandomDesk(size: Int): Unit =
    desk = new DeskCreateRandomStrategy().createDesk(size)
    gameStatus = GameStatus.NEW
    notifyObserver

  def createContinentMapDesk(): Unit =
    desk = new DeskCreateContinentMapStrategy().createDesk()
    gameStatus = GameStatus.NEW
    notifyObserver

  def createWorldMapDesk(): Unit =
    desk = new DeskCreateWorldMapStrategy().createDesk()
    gameStatus = GameStatus.NEW
    notifyObserver

  def set(row: Int, col: Int, field: Field): Unit =
    undoManager.doStep(new SetFieldCommand(row, col, field, this))
    gameStatus = GameStatus.SET
    notifyObserver

  def chooseFieldShowFriendlies(row: Int, col: Int): Unit =
    resetHighlight()
    desk = desk.chooseFieldShowFriendlyNeighbors(desk.neighbors(row, col))
    notifyObserver

  def resetHighlight(): Unit =
    desk = desk.resetHighlight
    notifyObserver

  def chooseFieldShowEnemies(row: Int, col: Int): Unit =
    resetHighlight()
    desk = desk.chooseFieldShowEnemyNeighbors(desk.neighbors(row, col))
    notifyObserver

  def undo(): Unit =
    undoManager.undoStep()
    gameStatus = GameStatus.UNDO
    notifyObserver

  def redo(): Unit =
    undoManager.redoStep()
    gameStatus = GameStatus.REDO
    notifyObserver

  def currentPlayerTurnToString: String = desk.currentPlayerTurn.toString

  def deskToString: String = desk.toString
}
