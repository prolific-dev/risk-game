package de.htwg.se.riskgame.controller.controllerComponent.controllerBasicImpl

import de.htwg.se.riskgame.model.deskComponent.DeskInterface
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.Desk
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.Team
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.Field
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.AreaField
import de.htwg.se.riskgame.util.Observable
import de.htwg.se.riskgame.util.UndoManager
import de.htwg.se.riskgame.controller.controllerComponent.ControllerInterface
import de.htwg.se.riskgame.controller.GameStatus
import de.htwg.se.riskgame.model.fileIoComponent.FileIoInterface
import de.htwg.se.riskgame.model.fileIoComponent.fileIoJsonImpl.FileIo
import de.htwg.se.riskgame.model.mapCreatorComponent.MapCreatorTemplate
import de.htwg.se.riskgame.model.mapCreatorComponent.mapCreatorDefault.MapCreator

class Controller(var desk: DeskInterface) extends Observable with ControllerInterface:

    val mapCreator : MapCreatorTemplate = new MapCreator
    val undoManager: UndoManager        = new UndoManager
    val fileIo     : FileIoInterface    = new FileIo

    var gameStatus: GameStatus = GameStatus.IDLE

    override def attackField(): Unit =
        undoManager.doStep(new AttackCommand(
            desk.info.selectedFieldPrimary,
            desk.info.selectedFieldSecondary,
            this))
        notifyObserver()

    override def clearSelectedFields(): Unit =
        desk = desk.clearSelectedFields()
        notifyObserver()

    override def selectFieldById(id: Int): Unit =
        undoManager.doStep(new SelectByIdCommand(id, this))
        notifyObserver()

    override def selectFieldByCoords(row: Int, col: Int): Unit =
        undoManager.doStep(new SelectByCoordsCommand(row, col, this))
        notifyObserver()

    override def setField(id: Int, soldiers: Int, team: Team): Unit =
        desk = desk.replaceFieldById(id, soldiers, team)
        notifyObserver()

    override def createDesk(): Unit =
        desk = new Desk(mapCreator.createMap)
        notifyObserver()

    override def undo(): Unit =
        undoManager.undoStep()
        notifyObserver()

    override def redo(): Unit =
        undoManager.redoStep()
        notifyObserver()

    override def save(): Unit =
     fileIo.save(desk)
     notifyObserver()

    override def load(): Unit =
     desk = fileIo.load
     notifyObserver()
    override def deskToString(): String = desk.toString

