package de.htwg.se.riskgame.aview.gui

import de.htwg.se.riskgame.controller.controllerComponent.ControllerInterface
import de.htwg.se.riskgame.model.teamComponent.Team
import scalafx.scene.layout.Pane
import scalafx.scene.paint.Color.{Blue, Red, Yellow, rgb}

class MapPane(controller: ControllerInterface) extends Pane {
  var childrenSeq: Seq[SVGField] = Seq()
  for {
    i <- 0 until controller.desk.size
    j <- 0 until controller.desk.size
  } {
    val field = controller.desk.field(i, j)
    val color = if (field.getHighlight) Yellow else field.team match {
      case Team.NO_TEAM => rgb(217, 182, 80, 1)
      case Team.BLUE => Blue
      case Team.RED => Red
    }

    childrenSeq = childrenSeq.appended(SVGField(field.getName, color, new SVGPathContinentMap))
  }
  children = childrenSeq
}
