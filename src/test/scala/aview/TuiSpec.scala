package de.htwg.se.riskgame.aview

import de.htwg.se.riskgame.controller.Controller
import de.htwg.se.riskgame.model.Desk
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TuiSpec extends AnyWordSpec with Matchers {
  "A Sudoku Tui" should {
    val controller = new Controller(new Desk(3))
    val tui = new Tui(controller)
    "create an empty Risk Game on input 'n'" in {
      tui.processInputLine("n")
      controller.desk should be(new Desk(3))
    }
    "create a random Risk Game on input 'r'" in {
      tui.processInputLine("r")
      // test
    }
  }
}
