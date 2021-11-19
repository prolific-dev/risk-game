package de.htwg.se.riskgame.controller

import de.htwg.se.riskgame.model.{Desk, DeskCreateRandomStrategy, Field, Team}
import de.htwg.se.riskgame.util.{Observable, UndoManager}

class Controller(var desk: Desk) extends Observable {
  var gameStatus: GameStatus = GameStatus.IDLE
  private val undoManager = new UndoManager

  def createEmptyDesk(size: Int): Unit = {
    desk = new Desk(size)
    gameStatus = GameStatus.EMPTY
    notifyObserver
  }

  def createRandomDesk(size: Int): Unit = {
    desk = new DeskCreateRandomStrategy().createDesk(size)
    gameStatus = GameStatus.NEW
    notifyObserver
  }

  def set(row: Int, col: Int, field: Field): Unit = {
    undoManager.doStep(new SetFieldCommand(row, col, field, this))
    gameStatus = GameStatus.SET
    notifyObserver
  }

  def undo: Unit = {
    undoManager.undoStep
    gameStatus = GameStatus.UNDO
    notifyObserver
  }

  def redo: Unit = {
    undoManager.redoStep
    gameStatus = GameStatus.REDO
    notifyObserver
  }

  def deskToString: String = desk.toString
}
