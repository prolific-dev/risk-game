package de.htwg.se.riskgame.controller

import de.htwg.se.riskgame.model.{Desk, DeskCreator, Field, Team}
import de.htwg.se.riskgame.util.{Observable, UndoManager}

class Controller(var desk: Desk) extends Observable {
  private val undoManager = new UndoManager

  def createEmptyDesk(size: Int): Unit = {
    desk = new Desk(size)
    notifyObserver
  }

  def createRandomDesk(size: Int, teams: Seq[Team], randomFields: Int): Unit = {
    desk = new DeskCreator(size, teams).createRandom(randomFields)
    notifyObserver
  }

  def set(row: Int, col: Int, field: Field): Unit = {
    undoManager.doStep(new SetFieldCommand(row, col, field, this))
    notifyObserver
  }

  def setColorizedOn: Unit = {
    desk = desk.setConsoleColorOn()
    notifyObserver
  }

  def setColorizedOff: Unit = {
    desk = desk.setConsoleColorOff()
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
