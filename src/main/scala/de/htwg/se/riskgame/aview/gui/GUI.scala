package de.htwg.se.riskgame.aview.gui

import de.htwg.se.riskgame.controller.controllerComponent.ControllerInterface
import de.htwg.se.riskgame.model.deskComponent.DeskInterface
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.Field
import de.htwg.se.riskgame.model.fileIoComponent.FileIOInterface
import de.htwg.se.riskgame.model.fileIoComponent.fileIoJsonImpl.{FileIO, MapData}
import de.htwg.se.riskgame.model.teamComponent.Team
import de.htwg.se.riskgame.model.troopComponent.Troop
import de.htwg.se.riskgame.util.Observer
import scalafx.application.JFXApp3
import scalafx.beans.property.StringProperty
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Menu, MenuBar, MenuItem}
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.{BorderPane, HBox, Pane, VBox}
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.{Blue, Red, Yellow, rgb}
import scalafx.scene.shape.SVGPath
import scalafx.scene.text.Text

class GUI(controller: ControllerInterface) extends JFXApp3 with Observer {
  controller.add(this)
  val map: MapPane = MapPane(controller)

  override def update: Unit = start()

  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      title = "Risk Game"
      width = 1000
      height = 800
      scene = new Scene {
        root = new BorderPane {
          style = "-fx-background-color: #3987c9"
          top = new MenuBar {
            menus = Seq(
              new Menu("File") {
                items = Seq(
                  new MenuItem("Exit")
                )
              },
              new Menu("Help") {
                items = Seq(
                  new MenuItem("GitHub")
                )
              }
            )
          }
          center = new VBox {
            padding = Insets(0, 0, 0, 1)
            children = Seq(
              new MapPane(controller) {}
            )
          }
          bottom = new VBox {
            children = new HBox {
              minHeight = 115.0
              maxHeight = 115.0
              spacing = 200.0
              visible = true
              style = "-fx-background-color: #620808"
              children = Seq(
                new Button("New") {
                  margin = Insets(10, 0, 0, 10)
                  style = "background-color: #4CAF50; color: #4CAF50; border: none; padding: 10px 24px; text-align: center; font-size: 12px"
                  onMouseClicked = e => controller.createContinentMapDesk()
                },
                new Button("Set") {
                  margin = Insets(10, 0, 0, 0)
                  onMouseClicked = e => controller.set(1, 0, Field("North America", Troop(3, Team.BLUE)))
                },
                new Button("Undo") {
                  margin = Insets(10, 0, 0, 0)
                  onMouseClicked = e => controller.undo()
                },
                new Button("Choose") {
                  margin = Insets(10, 0, 0, 0)
                  onMouseClicked = e => controller.chooseFieldShowEnemies(1, 0)
                },
                new Text("Player-turn: " + controller.currentPlayerTurnToString) {
                  margin = Insets(10, 10, 0, 0)
                  style = "-fx-font-size: 24px; -fx-font-weight: 500; color: #92a8d1;"
                }
              )
            }
          }
        }
      }
    }
  }
}

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

//stage = new JFXApp3.PrimaryStage {
//  title = "Risk Game"
//  scene = new Scene {
//    fill = Color.rgb(38, 38, 38)
//    content = new HBox {
//      padding = Insets(50, 80, 50, 80)
//      children = Seq(
//        new Text {
//          text = "Risk "
//          style = "-fx-font: normal bold 100pt sans-serif"
//          fill = new LinearGradient(
//            endX = 0,
//            stops = Stops(Red, DarkRed)
//          )
//        },
//        new Text {
//          text = "Game"
//          style = "-fx-font: italic bold 100pt sans-serif"
//          fill = new LinearGradient(
//            endX = 0,
//            stops = Stops(White, DarkGray)
//          )
//          effect = new DropShadow {
//            color = DarkGray
//            radius = 15
//            spread = 0.25
//          }
//        }
//      )
//    }
//  }
//}
