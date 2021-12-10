package de.htwg.se.riskgame.aview.GUI

import de.htwg.se.riskgame.controller.Controller
import de.htwg.se.riskgame.util.Observer
import scalafx.application.JFXApp3
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.{Menu, MenuBar, MenuItem}
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.{BorderPane, HBox, Pane, VBox}
import scalafx.scene.paint.*
import scalafx.scene.paint.Color.*
import scalafx.scene.text.Text

class GUI(controller: Controller) extends JFXApp3 with Observer {
  controller.add(this)

  override def update: Unit = {}

  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      title = "Risk Game"
      width = 900
      height = 600
      scene = new Scene {
        fill = Color.LightGray
        root = new BorderPane {
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
          left = new VBox {
            children = Seq(
              new Text("Left"),
              new Text("VBox child")
            )
          }
        }
      }
    }
  }
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

