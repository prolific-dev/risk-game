package de.htwg.se.riskgame.aview

import de.htwg.se.riskgame.controller.Controller
import de.htwg.se.riskgame.model.{Desk, Field, Team, Troop}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TuiSpec extends AnyWordSpec with Matchers {
  "A Sudoku Tui" should {
    val controller = new Controller(new Desk(3))
    val tui = new Tui(controller)
    "create an empty Risk Game on input 'n'" in {
      tui.processInputLine("n")
      controller.desk should be(new Desk(3))
      controller.desk.size should be(3)
    }
    "create a random Risk Game on input 'r'" in {
      tui.processInputLine("r")
      controller.desk.valid() should be(true)
    }
    "set a field on input 's'" in {
      tui.processInputLine("s")
      controller.desk.field(0, 0) should be(Field("Field", Troop(3, Team.BLUE)))
    }
    "set colorization on with input 'c on'" in {
      tui.processInputLine("c on")
      controller.desk.isColorized should be(true)
    }
    "set colorization off with input 'c off'" in {
      tui.processInputLine("c off")
      controller.desk.isColorized should be(false)
    }
    "on input _" in {
      val oldDesk = controller.desk
      tui.processInputLine("")
      controller.desk.equals(oldDesk) should be(true)
    }
  }
}
