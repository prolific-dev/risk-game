package de.htwg.se.riskgame.aview.gui

import de.htwg.se.riskgame.controller.controllerComponent.ControllerInterface
import de.htwg.se.riskgame.model.fileIoComponent.FileIOInterface
import de.htwg.se.riskgame.model.fileIoComponent.fileIoJsonImpl.FileIO
import de.htwg.se.riskgame.model.teamComponent.Team
import scalafx.scene.layout.Pane
import scalafx.scene.paint.Color.{Blue, Red, Yellow, rgb}

class MapPane(controller: ControllerInterface) extends Pane {
  val fileIO: FileIOInterface = new FileIO
  val size: Int = controller.desk.size
  var childrenSeq: Seq[SVGField] = Seq()
  for {
    i <- 0 until size
    j <- 0 until size
  } yield {
    val field = controller.desk.field(i, j)
    val color = if (field.getHighlight) Yellow else field.team match {
      case Team.NO_TEAM => rgb(217, 182, 80, 1)
      case Team.BLUE => Blue
      case Team.RED => Red
    }
    childrenSeq = childrenSeq.appended(SVGField(field.getName, color, fileIO.loadGuiMapDataPath()))
  }
  children = childrenSeq
}
