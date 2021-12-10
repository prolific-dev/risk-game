package de.htwg.se.riskgame.aview

import de.htwg.se.riskgame.controller.Controller
import de.htwg.se.riskgame.util.Observer
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.layout.StackPane
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.DeepSkyBlue
import scalafx.scene.shape.Rectangle

class GUI(controller: Controller) extends JFXApp3 with Observer {
  controller.add(this)

  def run(): Unit =
    println("GUI is starting...")
    start()

  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      title = "Simple ScalaFX App"
      scene = new Scene {
        root = new StackPane {
          padding = Insets(20)
          content = new Rectangle {
            width = 200
            height = 200
            fill = DeepSkyBlue
          }
        }
      }
    }
  }

  override def update: Unit = println("GUI updated")

}
