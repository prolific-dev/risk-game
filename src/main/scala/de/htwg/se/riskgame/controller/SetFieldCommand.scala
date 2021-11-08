package de.htwg.se.riskgame.controller

import de.htwg.se.riskgame.model.{Field, OccupiedField}
import de.htwg.se.riskgame.util.Command

class SetFieldCommand(row: Int, col: Int, field: Field, controller: Controller) extends Command {
  override def doStep: Unit = controller.desk = controller.desk.set(row, col, field)

  override def undoStep: Unit = controller.desk = controller.desk.set(row, col, new OccupiedField())

  override def redoStep: Unit = controller.desk = controller.desk.set(row, col, field)
}
