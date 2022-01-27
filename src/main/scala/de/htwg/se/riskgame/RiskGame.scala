package de.htwg.se.riskgame


import com.google.inject.{Guice, Injector}
import de.htwg.se.riskgame.RiskGame.controller
import de.htwg.se.riskgame.aview.gui.GUI
import de.htwg.se.riskgame.aview.tui.TUI
import de.htwg.se.riskgame.controller.controllerComponent.ControllerInterface
import de.htwg.se.riskgame.controller.controllerComponent.controllerBasicImpl.Controller
import de.htwg.se.riskgame.model.deskComponent.DeskInterface
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.Desk
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.deskCreatorComponent.deskCreatorBasicImpl.DeskCreateContinentMapStrategy
import scalafx.application.JFXApp3

import scala.io.StdIn.readLine


//@main def run: Unit =
//  println("Hello")
//  RiskGame.runTUI()


object RiskGame {
  val injector: Injector = Guice.createInjector(new RiskGameModule)
  val desk: DeskInterface = injector.getInstance(classOf[DeskInterface])
  val controller: ControllerInterface = new Controller(desk)
  val tui = new TUI(controller)

  def runTUI(): Unit =
    println("Welcome to Risk Game!\n")
    tui.run()

  def runGUI(): Unit =
    GUI(controller).start()
}

object RiskGameTUI extends App {
  RiskGame.runTUI()
}

//object RiskGameGUI extends JFXApp3 {
//  override def start(): Unit = RiskGame.runGUI()
//}


