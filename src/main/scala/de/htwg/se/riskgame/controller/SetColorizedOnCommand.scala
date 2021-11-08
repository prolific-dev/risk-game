package de.htwg.se.riskgame.controller

import de.htwg.se.riskgame.util.Command

class SetColorizedOnCommand(controller: Controller) extends Command {
  override def doStep: Unit = controller.desk = controller.desk.setColorizedOn()

  override def undoStep: Unit = controller.desk = controller.desk.setColorizedOff()

  override def redoStep: Unit = controller.desk = controller.desk.setColorizedOn()
}
