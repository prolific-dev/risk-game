package de.htwg.se.riskgame.controller.controllerComponent.controllerBasicImpl

import com.google.inject.{Guice, Inject, Injector}
import de.htwg.se.riskgame.RiskGameModule
import de.htwg.se.riskgame.controller.GameStatus
import de.htwg.se.riskgame.controller.controllerComponent.ControllerInterface
import de.htwg.se.riskgame.model.*
import de.htwg.se.riskgame.model.deskComponent.DeskInterface
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.*
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.deskCreatorComponent.deskCreatorBasicImpl.{DeskCreateContinentMapStrategy, DeskCreateRandomStrategy}
import de.htwg.se.riskgame.model.fileIoComponent.FileIOInterface
import de.htwg.se.riskgame.model.fileIoComponent.fileIoXmlImpl.FileIO
import de.htwg.se.riskgame.model.teamComponent.Team
import de.htwg.se.riskgame.util.{Observable, UndoManager}

class Controller(var desk: DeskInterface) extends Observable with ControllerInterface {
  val injector: Injector = Guice.createInjector(new RiskGameModule)
  val fileIO: FileIOInterface = injector.getInstance(classOf[FileIOInterface])
  var gameStatus: GameStatus = GameStatus.IDLE
  val undoManager = new UndoManager

  def createContinentMapDesk(): Unit =
    desk = new DeskCreateContinentMapStrategy().createDesk()
    gameStatus = GameStatus.NEW
    notifyObserver

  def createEmptyDesk(size: Int): Unit =
    desk = new Desk(size)
    gameStatus = GameStatus.EMPTY
    notifyObserver

  def createRandomDesk(size: Int): Unit =
    desk = new DeskCreateRandomStrategy().createDesk(size)
    gameStatus = GameStatus.NEW
    notifyObserver

  def chooseFieldShowEnemies(row: Int, col: Int): Unit =
    resetHighlight
    desk = desk.chooseFieldShowEnemyNeighbors(desk.neighbors(row, col))
    notifyObserver

  def chooseFieldShowFriendlies(row: Int, col: Int): Unit =
    resetHighlight
    desk = desk.chooseFieldShowFriendlyNeighbors(desk.neighbors(row, col))
    notifyObserver

  def exit: Unit =
    gameStatus = GameStatus.CLOSING
    notifyObserver
    sys.exit(0)

  def set(row: Int, col: Int, field: Field): Unit =
    resetHighlight
    undoManager.doStep(new SetFieldCommand(row, col, field, this))
    chooseField(row, col)
    gameStatus = GameStatus.SET
    notifyObserver

  def chooseField(row: Int, col: Int): Unit =
    resetHighlight
    desk = desk.chooseField(row, col)
    notifyObserver

  def resetHighlight: Unit =
    desk = desk.resetHighlight
    notifyObserver

  def undo: Unit =
    undoManager.undoStep()
    gameStatus = GameStatus.UNDO
    notifyObserver

  def redo: Unit =
    undoManager.redoStep()
    gameStatus = GameStatus.REDO
    notifyObserver

  def endTurn: Unit =
    resetHighlight
    resetChosenField
    desk = desk.endTurn
    notifyObserver

  def save: Unit =
    fileIO.save(desk)
    gameStatus = GameStatus.SAVED
    notifyObserver

  def load: Unit =
    desk = fileIO.load
    gameStatus = GameStatus.LOADED
    notifyObserver

  def resetChosenField: Unit =
    desk = desk.resetHighlight
    desk = desk.resetChosenField
    notifyObserver

  def getCurrentPlayerTurn: Team = desk.currentPlayerTurn

  def currentPlayerTurnToString: String = desk.currentPlayerTurn.toString

  def deskToString: String = desk.toString

  //  def createWorldMapDesk(): Unit =
  //    desk = new DeskCreateWorldMapStrategy().createDesk()
  //    gameStatus = GameStatus.NEW
  //    notifyObserver
}
