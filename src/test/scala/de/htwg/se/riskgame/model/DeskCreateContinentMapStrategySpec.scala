package de.htwg.se.riskgame.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class DeskCreateContinentMapStrategySpec extends AnyWordSpec with Matchers {
  "A DeskCreatorContinentMapStrategy" when {
    "called unedited" should {
      val desk = new DeskCreateContinentMapStrategy().createDesk()
      "have a fixed size of 4" in {
        desk.size should be(4)
      }
      "have the right string representation" in {
        desk.toString should be(
          "\n"
            + "+-" + "-----" + "-----" + "-----" + "-----" + "-+\n"
            + "| " + "  x  " + "  x  " + "  x  " + "  x  " + " |\n"
            + "| " + "  1  " + "  1  " + "  x  " + "  1  " + " |\n"
            + "| " + "  1  " + "  x  " + "  1  " + "  1  " + " |\n"
            + "| " + "  x  " + "  x  " + "  x  " + "  x  " + " |\n"
            + "+-" + "-----" + "-----" + "-----" + "-----" + "-+\n"
        )
      }
    }
  }
}
