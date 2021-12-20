package de.htwg.se.riskgame.aview.gui

import de.htwg.se.riskgame.controller.controllerComponent.ControllerInterface
import de.htwg.se.riskgame.model.deskComponent.DeskInterface
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.Field
import de.htwg.se.riskgame.model.teamComponent.Team
import de.htwg.se.riskgame.model.troopComponent.Troop
import de.htwg.se.riskgame.util.Observer
import scalafx.application.JFXApp3
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Menu, MenuBar, MenuItem}
import scalafx.scene.layout.{BorderPane, HBox, VBox}
import scalafx.scene.text.Text

class GUI(controller: ControllerInterface) extends JFXApp3 with Observer {
  controller.add(this)
  val map: MapPane = MapPane(controller)

  override def update: Unit = start()

  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      title.value = "Risk Game"
      scene = new Scene(1000, 800) {
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
