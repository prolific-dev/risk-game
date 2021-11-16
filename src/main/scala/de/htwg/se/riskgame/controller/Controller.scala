package de.htwg.se.riskgame.controller

import de.htwg.se.riskgame.model.{Desk, DeskCreateRandomStrategy, Field, Team}
import de.htwg.se.riskgame.util.{Observable, UndoManager}

class Controller(var desk: Desk) extends Observable {
  private val undoManager = new UndoManager

  def createEmptyDesk(size: Int): Unit = {
    desk = new Desk(size)
    notifyObserver
  }

  def createRandomDesk(size: Int): Unit = {
    desk = new DeskCreateRandomStrategy().createDesk(size)
    notifyObserver
  }

  def set(row: Int, col: Int, field: Field): Unit = {
    undoManager.doStep(new SetFieldCommand(row, col, field, this))
    notifyObserver
  }

  def undo: Unit = {
    undoManager.undoStep
    notifyObserver
  }

  def redo: Unit = {
    undoManager.redoStep
    notifyObserver
  }

  def deskToString: String = desk.toString
}
