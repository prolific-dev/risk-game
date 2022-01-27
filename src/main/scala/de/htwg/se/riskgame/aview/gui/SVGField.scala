package de.htwg.se.riskgame.aview.gui

import de.htwg.se.riskgame.controller.controllerComponent.ControllerInterface
import de.htwg.se.riskgame.model.deskComponent.DeskInterface
import de.htwg.se.riskgame.model.fileIoComponent.fileIoJsonImpl.MapData
import de.htwg.se.riskgame.model.teamComponent.Team
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.{GridPane, Pane}
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.{Blue, Red, Yellow, rgb}
import scalafx.scene.shape.SVGPath

case class SVGField(name: String, color: Color, map: Map[String, MapData]) extends SVGPath {
  id = name
  content = map(getId).path
  fill = color
  scaleX = 2.4
  scaleY = 2.4
  effect = new DropShadow()
  layoutX = map(getId).layoutX
  layoutY = map(getId).layoutY
}


