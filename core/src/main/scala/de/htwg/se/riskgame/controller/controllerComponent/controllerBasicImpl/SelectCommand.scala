package de.htwg.se.riskgame.controller.controllerComponent.controllerBasicImpl



import de.htwg.se.riskgame.controller.controllerComponent.controllerBasicImpl
import de.htwg.se.riskgame.model.deskComponent.DeskInterface
import de.htwg.se.riskgame.controller.controllerComponent.ControllerInterface

class SelectByIdCommand(id: Int, controller: ControllerInterface) extends Command:
    private var preMemento : DeskInterface = controller.desk
    private var pastMemento: DeskInterface = controller.desk

    private val handler = SelectHandler

    def doStep()  : Unit = controller.desk = controller.desk.selectFieldById(id)
    def undoStep(): Unit = controller.desk = preMemento
    def redoStep(): Unit = controller.desk = pastMemento

class SelectByCoordsCommand(row: Int, col: Int, controller: ControllerInterface) extends Command:
    private var memento: DeskInterface = controller.desk

    def doStep()  : Unit = controller.desk = controller.desk.selectFieldByCoords(row, col)
    def undoStep(): Unit = controller.desk = memento
    def redoStep(): Unit = controller.desk = controller.desk.selectFieldByCoords(row, col)