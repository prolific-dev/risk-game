package de.htwg.se.riskgame.controller.controllerComponent.controllerBasicImpl

import de.htwg.se.riskgame.controller.controllerComponent.Command
import de.htwg.se.riskgame.controller.controllerComponent.controllerBasicImpl.Controller
import de.htwg.se.riskgame.model.*
import de.htwg.se.riskgame.model.deskComponent.DeskInterface
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.Field

class SetFieldCommand(row: Int, col: Int, field: Field, controller: Controller) extends Command {
  var memento: DeskInterface = controller.desk

  override def doStep(): Unit = controller.desk = controller.desk.set(row, col, field)

  override def undoStep(): Unit = controller.desk = controller.desk.set(row, col, memento.field(row, col))

  override def redoStep(): Unit = controller.desk = controller.desk.set(row, col, field)
}
