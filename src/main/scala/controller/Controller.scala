package de.htwg.se.riskgame.controller

import de.htwg.se.riskgame.model.{Desk, DeskCreator, IField, Team}
import de.htwg.se.riskgame.util.Observable

class Controller(var desk: Desk) extends Observable {
  def createEmptyDesk(size: Int): Unit = {
    desk = new Desk(size)
    notifyObserver
  }

  def createRandomDesk(size: Int, teams: Seq[Team], randomFields: Int): Unit = {
    desk = new DeskCreator(size, teams).createRandom(randomFields)
    notifyObserver
  }

  def set(row: Int, col: Int, field: IField): Unit = {
    desk = desk.set(row, col, field)
    notifyObserver
  }

  def setColorizedOn: Unit = {
    desk = desk.copy(isColorized = true)
    notifyObserver
  }

  def setColorizedOff: Unit = {
    desk = desk.copy(isColorized = false)
    notifyObserver
  }

  def deskToString: String = desk.toString
}
