package de.htwg.se.riskgame.controller

import de.htwg.se.riskgame.util.Command

class SetColorizedOffCommand(controller: Controller) extends Command {
  override def doStep: Unit = controller.desk = controller.desk.setColorizedOff()

  override def undoStep: Unit = controller.desk = controller.desk.setColorizedOn()

  override def redoStep: Unit = controller.desk = controller.desk.setColorizedOff()

}
