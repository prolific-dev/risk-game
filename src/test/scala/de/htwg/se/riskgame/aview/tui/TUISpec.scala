package de.htwg.se.riskgame.aview.tui

import de.htwg.se.riskgame.aview.tui.TUI
import de.htwg.se.riskgame.controller.controllerComponent.controllerBasicImpl.Controller
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.{Desk, Field}
import de.htwg.se.riskgame.model.teamComponent.Team
import de.htwg.se.riskgame.model.troopComponent.Troop
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.io.AnsiColor

class TUISpec extends AnyWordSpec with Matchers {
  "A Sudoku tui" should {
    val controller = new Controller(new Desk(3))
    val tui = new TUI(controller)
    "create an empty Risk Game on input 'n'" in {
      tui.processInputLine("n")
      controller.desk should be(new Desk(3))
      controller.desk.size should be(3)
    }
    "choose friendly neighbors and highlight them" in {
      tui.processInputLine("c f")
      controller.desk.field(0, 0).toString should be(AnsiColor.YELLOW + "1" + AnsiColor.RESET)
      controller.desk.field(0, 1).toString should be(AnsiColor.YELLOW + "1" + AnsiColor.RESET)
      controller.desk.field(1, 1).toString should be(AnsiColor.YELLOW + "1" + AnsiColor.RESET)
      controller.desk.field(2, 0).toString should be(AnsiColor.YELLOW + "1" + AnsiColor.RESET)
      controller.desk.field(2, 1).toString should be(AnsiColor.YELLOW + "1" + AnsiColor.RESET)
    }
    "set a field on input 's'" in {
      tui.processInputLine("s")
      controller.desk.field(1, 0).getTroop should be(Some(Troop(3, Team.BLUE)))
    }
    "choose enemy neighbors and highlight them" in {
      tui.processInputLine("c e")
      controller.desk.field(2, 0).toString should be(AnsiColor.YELLOW + "1" + AnsiColor.RESET)
    }
    "reset highlight of all fields" in {
      tui.processInputLine("r h")
      for {
        i <- 0 until controller.desk.size
        j <- 0 until controller.desk.size
      } {
        controller.desk.field(i, j).getHighlight should be(false)
      }
    }
    "create a random Risk Game on input 'r'" in {
      tui.processInputLine("r")
      controller.desk.valid should be(true)
    }
    "create a world map Risk Game on input 'w'" in {
      tui.processInputLine("w")
      controller.desk.valid should be(true)
      controller.desk.size should be(10)
    }
    "create a continent map Risk Game on input 'c'" in {
      tui.processInputLine("c")
      controller.desk.valid should be(true)
      controller.desk.size should be(4)
    }
    "on input _" in {
      val oldDesk = controller.desk
      tui.processInputLine("")
      controller.desk.equals(oldDesk) should be(true)
    }
  }
}
