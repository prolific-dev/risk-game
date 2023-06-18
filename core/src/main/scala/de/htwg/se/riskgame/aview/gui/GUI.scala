// package de.htwg.se.riskgame.aview.gui

// //import scalafx.application.JFXApp3
// //import scalafx.scene.Scene
// //import scalafx.scene.layout.{GridPane, StackPane, BorderPane}
// //import scalafx.scene.shape.{Rectangle, SVGPath}
// //import scalafx.scene.control.ScrollPane
// //import scalafx.scene.input.ScrollEvent
// //import scalafx.Includes._
// //import javafx.scene.input.{MouseButton, MouseEvent}
// //
// //object GUI extends JFXApp3 {
// //  override def start(): Unit = {
// //    val matrixSize = 5 // replace with your desired matrix size
// //
// //    val matrix = Array.fill(matrixSize, matrixSize)(new Rectangle {
// //      width = 50
// //      height = 50
// //      fill = scalafx.scene.paint.Color.White
// //      stroke = scalafx.scene.paint.Color.Black
// //    })
// //
// //    val svg = new SVGPath {
// //      content = "M 0,0 L 50,0 L 50,50 L 0,50 L 0,0 M 100,0 L 150,0 L 150,50 L 100,50 L 100,0" // replace with your desired SVG path
// //    }
// //
// //    val gridPane = new GridPane {
// //      hgap = 5
// //      vgap = 5
// //      padding = scalafx.geometry.Insets(10)
// //      for (i <- 0 until matrixSize; j <- 0 until matrixSize) {
// //        add(matrix(i)(j), i, j)
// //      }
// //    }
// //
// //    val zoomableGridPane = new ScrollPane {
// //      content = gridPane
// //      pannable = true
// //      onScroll = (event: ScrollEvent) => {
// //        if (event.deltaY < 0)
// //          scaleX = scaleX.value - 0.1
// //          scaleY = scaleY.value - 0.1
// ////          zoomFactor.value -= 0.1
// //        else if (event.deltaY > 0) {
// //          scaleX = scaleX.value + 0.1
// //          scaleY = scaleY.value + 0.1
// ////          zoomFactor.value += 0.1
// //        }
// //      }
// //    }
// //
// //    val myroot = new BorderPane {
// //      center = zoomableGridPane
// //      top = svg
// //    }
// //
// //    stage = new JFXApp3.PrimaryStage {
// //      title = "Zoomable GridPane Example"
// //      width = 800
// //      height = 600
// //      scene = new Scene {
// //        root = myroot
// //      }
// //    }
// //  }
// //}


// import scalafx.application.JFXApp3
// import scalafx.application.JFXApp3.PrimaryStage
// import scalafx.scene.Scene
// import scalafx.scene.paint.Color
// import scalafx.scene.paint.Color.Red
// import scalafx.scene.paint.Color.DarkRed
// import scalafx.scene.paint.Color.White
// import scalafx.scene.paint.Color.DarkGray
// import scalafx.scene.layout.{AnchorPane, GridPane, HBox, Region, StackPane}
// import scalafx.geometry.Insets
// import scalafx.scene.text.Text
// import scalafx.scene.paint.LinearGradient
// import scalafx.scene.paint.Stops
// import scalafx.scene.effect.DropShadow
// import scalafx.scene.control.SplitPane
//   object GUI extends JFXApp3 with:
//       override def start(): Unit =
//          stage = new JFXApp3.PrimaryStage {
//           title  = "Hidden Split Pane Example"
// //          width  = 600
// //          height = 400
//           maximized = true
//           scene  = new Scene {
//               root = {
//                   new AnchorPane {
//                       val hiddenSplitPaneCss = this.getClass.getResource("/styles/style.css").toExternalForm
//                       val reg1 = new Region {
//                           styleClass = List("rounded")
//                       }
//                       val reg2 = new Region {
//                           styleClass = List("rounded")
//                       }
//                       val reg3 = new Region {
//                           styleClass = List("rounded")
//                       }

//                       children = new StackPane {

//                         AnchorPane.setAnchors(this, 20, 10, 20, 10)

//                         children = new SplitPane {
//                           dividerPositions_=(0.20, 0.80)

//                           padding            = Insets(10)
//                           id                 = "hiddenSplitter"
//                           stylesheets       += hiddenSplitPaneCss
//                           items            ++= Seq(reg1, reg2, reg3)
//                         }
//                       }
//                   }
//               }
//           }
//          }
