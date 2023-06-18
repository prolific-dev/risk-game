package de.htwg.se.riskgame.aview.gui

import com.google.inject.{Guice, Injector}
import de.htwg.se.riskgame.{RiskGame, RiskGameModule}
import de.htwg.se.riskgame.controller.controllerComponent.ControllerInterface
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.Field
import de.htwg.se.riskgame.model.deskComponent.DeskInterface
import de.htwg.se.riskgame.model.fileIoComponent.fileIoJsonImpl.{FileIO, MapData}
import de.htwg.se.riskgame.model.fileIoComponent.FileIOInterface
import de.htwg.se.riskgame.model.teamComponent.Team
import de.htwg.se.riskgame.model.troopComponent.Troop
import de.htwg.se.riskgame.util.Observer
import scalafx.application.{HostServices, JFXApp3}
import scalafx.application.JFXApp3.{PrimaryStage, Stage}
import scalafx.beans.property.{ObjectProperty, StringProperty}
import scalafx.collections.ObservableBuffer
import scalafx.geometry.Insets
import scalafx.geometry.Pos.Center
import scalafx.scene.control.*
import scalafx.scene.control.SplitPane.Divider
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.Priority.{Always, Never}
import scalafx.scene.layout.*
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.{color, rgb, Blue, Brown, Red, RosyBrown, Yellow}
import scalafx.scene.shape.SVGPath
import scalafx.scene.text.{Font, FontWeight, Text}
import scalafx.scene.Scene
import scalafx.scene.image.Image

import javax.swing.JDesktopPane

class GUI(controller: ControllerInterface) extends JFXApp3 with Observer {
  controller.add(this)
  var currentStage = "WelcomeStage"

  override def update: Unit = start()

  override def start(): Unit =
    stage = new PrimaryStage {
      title = "Risk Game"
      icons += new Image(getClass.getResourceAsStream("/icons/knight.png"))
      width = 1400
      height = 800
      scene = new Scene {
        val mapPane = new MapPane
        root = new VBox {
          margin = Insets(0, 0, 0, 0)
          padding = Insets(0, 0, 0, 0)
          spacing = 0
          vgrow = Never
          fillWidth = true
          children = Seq(
            new MenuBar {
              menus = Seq(
                new Menu("File") {
                  items = Seq(
                    new MenuItem("New") {
                      onAction = e => controller.createContinentMapDesk()
                    },
                    new MenuItem("Load") {
                      onAction = e => controller.load
                    },
                    new MenuItem("Save") {
                      onAction = e => controller.save
                    },
                    new MenuItem("Exit") {
                      onAction = e => controller.exit
                    }
                  )
                },
                new Menu("Edit") {
                  items = Seq(
                    new MenuItem("Undo") {
                      onAction = e => controller.undo
                    },
                    new MenuItem("Redo") {
                      onAction = e => controller.redo
                    }
                  )
                },
                new Menu("Help") {
                  items = Seq(
                    new MenuItem("GitHub") {}
                  )
                }
              )
            },
            new SplitPane {
              margin = Insets(0, 0, 0, 0)
              padding = Insets(0, 0, 0, 0)
              vgrow = Always
              items ++= Seq(
                new AnchorPane {
                  val field: Option[Field] = controller.desk.info.chosenField
                  var actionButton1: Button = new Button("") {
                    visible = false
                  }
                  var actionButton2: Button = new Button("") {
                    visible = false
                  }
                  var actionButton3: Button = new Button("") {
                    visible = false
                  }
                  val actionButtonStyle: String = "-fx-background-color:\n " +
                    "linear-gradient(#ffd65b, #e68400),\n" +
                    "linear-gradient(#ffef84, #f2ba44),\n" +
                    "linear-gradient(#ffea6a, #efaa22),\n" +
                    "linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n" +
                    "linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\n" +
                    "-fx-background-radius: 30;\n" +
                    "-fx-background-insets: 0,1,2,3,0;\n" +
                    "-fx-text-fill: #654b00;\n" +
                    "-fx-font-weight: bold;\n" +
                    "-fx-font-size: 14px;\n" +
                    "-fx-padding: 10 20 10 20;"
                  if (field.isDefined && field.get.getName != "x") {
                    val child: Seq[SVGField] = mapPane.childrenSeq.filter(svgField => svgField.getId.equals(field.get.getName))
                    val svgField: SVGField = child.head
                    actionButton1 = new Button("Show Friendlies") {
                      style = actionButtonStyle
                      onMouseClicked = e => controller.chooseFieldShowFriendlies(svgField.row, svgField.col)
                    }
                    actionButton2 = new Button("Show Enemies") {
                      style = actionButtonStyle
                      onMouseClicked = e => controller.chooseFieldShowEnemies(svgField.row, svgField.col)
                    }
                    actionButton3 = new Button("Set Field") {
                      style = actionButtonStyle
                      onMouseClicked = e => controller.set(svgField.row, svgField.col, Field(field.get.getName, Troop(3, controller.getCurrentPlayerTurn)))
                    }
                  }
                  maxWidth = 200
                  padding = Insets(0, 0, 0, 0)
                  resizable = true
                  style = "-fx-background-color: #a0522d"
                  children = Seq(
                    new VBox {
                      margin = Insets(0, 0, 0, 0)
                      padding = Insets(0, 0, 0, 0)
                      spacing = 50
                      vgrow = Always
                      fillWidth = true
                      children = Seq(
                        new Label("Action") {
                          font = Font.font("Comic Sans MS", FontWeight.Bold, 22)
                        }
                        ,
                        actionButton1,
                        actionButton2,
                        actionButton3,
                        new Button("End Turn") {
                          style = actionButtonStyle
                          onMouseClicked = e => controller.endTurn
                        }
                      )
                    }
                  )
                  onMouseClicked = e => controller.resetChosenField
                },
                mapPane,
                new AnchorPane {
                  val field: Option[Field] = controller.desk.info.chosenField
                  val fieldLabel: Label = new Label("")
                  if (field.isDefined && field.get.getName != "x") {
                    fieldLabel.setText(
                      "\n"
                        + field.get.getName
                        + "\nTroops: "
                        + field.get.getTroop.get.amount.toString
                        + "\nTeam: "
                        + field.get.getTroop.get.team.toString
                    )
                    fieldLabel.setFont(Font.font("Comic Sans MS", 18))
                  }
                  maxWidth = 200
                  padding = Insets(0, 0, 0, 0)
                  resizable = true
                  style = "-fx-background-color: #a0522d"
                  children = Seq(
                    new VBox {
                      margin = Insets(0, 0, 0, 0)
                      padding = Insets(0, 0, 0, 0)
                      spacing = 5
                      vgrow = Always
                      fillWidth = true
                      children = Seq(
                        new Label("Details") {
                          font = Font.font("Comic Sans MS", FontWeight.Bold, 22)
                        },
                        fieldLabel
                      )
                    }
                  )
                }
              )
            },
            new HBox {
              margin = Insets(0, 0, 0, 0)
              padding = Insets(3, 3, 3, 3)
              spacing = 5
              vgrow = Never
              fillHeight = true
              children = Seq(
                new Label(controller.gameStatus.toString) {
                  margin = Insets(0, 0, 0, 0)
                  padding = Insets(0, 0, 0, 0)
                  hgrow = Always
                },
                new Pane {
                  margin = Insets(0, 0, 0, 0)
                  padding = Insets(0, 0, 0, 0)
                  hgrow = Always
                },
                new Label("Player Turn: " + controller.currentPlayerTurnToString) {
                  margin = Insets(0, 0, 0, 0)
                  padding = Insets(0, 0, 0, 0)
                  hgrow = Never
                }
              )
            },
            new AnchorPane {
              margin = Insets(0, 0, 0, 0)
              padding = Insets(0, 0, 0, 0)
            }
          )
        }
      }
    }

  class MapPane extends AnchorPane {
    val injector: Injector = Guice.createInjector(new RiskGameModule)
    val fileIO: FileIOInterface = injector.getInstance(classOf[FileIOInterface])
    val size: Int = controller.desk.size
    var childrenSeq: Seq[SVGField] = Seq()
    for {
      i <- 0 until size
      j <- 0 until size
    } yield {
      childrenSeq = childrenSeq.appended(SVGField(i, j, fileIO.loadGuiMapDataPath()))
    }
    style = "-fx-background-color: #3987c9"
    children = childrenSeq
    margin = Insets(0, 0, 0, 0)
  }

  case class SVGField(row: Int, col: Int, map: Map[String, MapData]) extends SVGPath {
    val field: Field = controller.desk.field(row, col)
    id = field.getName
    content = map(getId).path
    fill =
      if (field.getHighlight) Yellow
      else
        field.team match {
          case Team.NO_TEAM => rgb(217, 182, 80, 1)
          case Team.BLUE => Blue
          case Team.RED => Red
        }
    scaleX = 2.4
    scaleY = 2.4
    effect = new DropShadow()
    layoutX = map(getId).layoutX
    layoutY = map(getId).layoutY

    onMouseClicked = e => controller.chooseField(row, col)
  }
}





//new AnchorPane {
//  padding = Insets(0, 0, 0, 0)
//  resizable = true
//  children = Seq(
//    new Label("Details")
//  )
//}

//  override def start(): Unit = {
//    stage = new JFXApp3.PrimaryStage {
//      title = "Risk Game"
//      width = 1200
//      height = 800
//      scene = new Scene {
//        root = new BorderPane {
//          style = "-fx-background-color: #3987c9"
//          top = new MenuBar {
//            menus = Seq(
//              new Menu("File") {
//                items = Seq(
//                  new MenuItem("Load") {
//                    onAction = e => controller.load
//                  },
//                  new MenuItem("Save") {
//                    onAction = e => controller.save
//                  },
//                  new MenuItem("Exit") {
//                    onAction = e => controller.exit
//                  }
//                )
//              },
//              new Menu("Help") {
//                items = Seq(
//                  new MenuItem("GitHub") {}
//                )
//              }
//            )
//          }
//          left = new Pane {
//            fill = Color.Green
//            padding = Insets(10, 10, 10, 10)
//          }
//          center = new HBox {
//            alignment = Center
//            fillHeight = true
//            vgrow = Always
//            hgrow = Always
//            //spacing = 50
//            children = Seq(
//              new MapPane(controller) {}
//            )
//          }
//          bottom = new VBox {
//            children = new HBox {
//              minHeight = 115.0
//              maxHeight = 115.0
//              spacing = 200.0
//              visible = true
//              style = "-fx-background-color: #620808"
//              children = Seq(
//                new Button("New") {
//                  margin = Insets(10, 0, 0, 0)
//                  style = "background-color: #4CAF50; color: #4CAF50; border: none; padding: 10px 24px; text-align: center; font-size: 12px"
//                  onMouseClicked = e => controller.createContinentMapDesk()
//                },
//                new Button("Set") {
//                  margin = Insets(10, 0, 0, 0)
//                  vgrow = Priority.Always
//                  onMouseClicked = e => controller.set(1, 0, Field("North America", Troop(3, Team.BLUE)))
//                },
//                new Button("Undo") {
//                  margin = Insets(10, 0, 0, 0)
//                  onMouseClicked = e => controller.undo()
//                },
//                new Button("Choose") {
//                  margin = Insets(10, 0, 0, 0)
//                  onMouseClicked = e => controller.chooseFieldShowEnemies(1, 0)
//                },
//                new Text("Player-turn: " + controller.currentPlayerTurnToString) {
//                  margin = Insets(10, 10, 0, 0)
//                  style = "-fx-font-size: 24px; -fx-font-weight: 500; color: #92a8d1;"
//                }
//              )
//            }
//          }
//        }
//      }
//    }
//  }
//}
