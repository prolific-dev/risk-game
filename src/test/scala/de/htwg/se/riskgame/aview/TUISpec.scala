package de.htwg.se.riskgame.aview

import de.htwg.se.riskgame.aview.TUI.TUI
import de.htwg.se.riskgame.controller.Controller
import de.htwg.se.riskgame.model.{Desk, OccupiedField, Team, Troop}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.io.AnsiColor

class TUISpec extends AnyWordSpec with Matchers {
  "A Sudoku TUI" should {
    val controller = new Controller(new Desk(3))
    val tui = new TUI(controller)
    "create an empty Risk Game on input 'n'" in {
      tui.processInputLine("n")
      controller.desk should be(new Desk(3))
      controller.desk.size should be(3)
    }
    "create a random Risk Game on input 'r'" in {
      tui.processInputLine("r")
      controller.desk.valid should be(true)
    }
    "set a field on input 's'" in {
      tui.processInputLine("s")
      controller.desk.field(0, 0).getName should be("OccupiedField")
      controller.desk.field(0, 0).getTroop should be(Some(Troop(3, Team.BLUE)))
    }
    "choose friendly neighbors and highlight them" in {
      tui.processInputLine("c f")
      controller.desk.field(0, 1).toString should be(AnsiColor.YELLOW + "3" + AnsiColor.RESET)

    }
    "choose enemy neighbors and highlight them" in {
      tui.processInputLine("c e")
      controller.desk.field(1, 0).toString should be(AnsiColor.YELLOW + "3" + AnsiColor.RESET)
    }
    "reset highlight of all fields" in {
      tui.processInputLine("r h")
      controller.desk.field(0, 1).toString should be(AnsiColor.BLUE + "3" + AnsiColor.RESET)
      controller.desk.field(1, 0).toString should be(AnsiColor.RED + "3" + AnsiColor.RESET)
    }
    "on input _" in {
      val oldDesk = controller.desk
      tui.processInputLine("")
      controller.desk.equals(oldDesk) should be(true)
    }
  }
}
