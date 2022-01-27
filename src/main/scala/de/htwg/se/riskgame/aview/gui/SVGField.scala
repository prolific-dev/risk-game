package de.htwg.se.riskgame.aview.gui

import de.htwg.se.riskgame.controller.controllerComponent.ControllerInterface
import de.htwg.se.riskgame.model.deskComponent.DeskInterface
import de.htwg.se.riskgame.model.teamComponent.Team
import scalafx.Includes.*
import scalafx.beans.binding.Bindings.*
import scalafx.beans.property.ObjectProperty
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Node
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.{GridPane, Pane}
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.{Blue, Red, Yellow, rgb}
import scalafx.scene.shape.SVGPath

case class SVGField(name: String, color: Color, pathMap: Map[String, String]) extends SVGPath {
  id = name
  content = pathMap(getId)
  fill = color
  scaleX = 2.4
  scaleY = 2.4
  effect = new DropShadow()
  layoutX = getId match {
    case "North America" => 65
    case "South America" => 140
    case "Europe" => 350
    case "Africa" => 340
    case "Asia" => 605
    case "Australia" => 730
    case _ => 0
  }
  layoutY = getId match {
    case "North America" => 60
    case "South America" => 280
    case "Europe" => 10
    case "Africa" => 190
    case "Asia" => 63
    case "Australia" => 300
    case _ => 0
  }
}


