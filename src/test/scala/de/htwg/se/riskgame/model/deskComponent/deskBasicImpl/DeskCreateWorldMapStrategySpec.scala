package de.htwg.se.riskgame.model.deskComponent.deskBasicImpl

import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.DeskCreateWorldMapStrategy
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class DeskCreateWorldMapStrategySpec extends AnyWordSpec with Matchers {
  "A DeskCreateWorldMapStrategySpec" when {
    "called unedited" should {
      val desk = new DeskCreateWorldMapStrategy().createDesk()
      "have a fixed size of 10" in {
        desk.size should be(10)
      }
      "have the right string representation" in {
        desk.toString should be(
          "\n"
            + "+-" + "-----" + "-----" + "-----" + "-----" + "-----" + "-----" + "-----" + "-----" + "-----" + "-----" + "-+\n"
            + "| " + "  x  " + "  x  " + "  x  " + "  x  " + "  x  " + "  x  " + "  x  " + "  x  " + "  x  " + "  x  " + " |\n"
            + "| " + "  x  " + "  x  " + "  x  " + "  1  " + "  x  " + "  x  " + "  x  " + "  x  " + "  x  " + "  x  " + " |\n"
            + "| " + "  1  " + "  1  " + "  1  " + "  x  " + "  1  " + "  1  " + "  1  " + "  1  " + "  1  " + "  x  " + " |\n"
            + "| " + "  1  " + "  1  " + "  x  " + "  x  " + "  1  " + "  1  " + "  1  " + "  1  " + "  x  " + "  x  " + " |\n"
            + "| " + "  1  " + "  x  " + "  x  " + "  x  " + "  1  " + "  1  " + "  1  " + "  x  " + "  1  " + "  x  " + " |\n"
            + "| " + "  x  " + "  1  " + "  1  " + "  1  " + "  x  " + "  1  " + "  x  " + "  x  " + "  1  " + "  1  " + " |\n"
            + "| " + "  x  " + "  x  " + "  1  " + "  x  " + "  x  " + "  1  " + "  x  " + "  x  " + "  x  " + "  1  " + " |\n"
            + "| " + "  x  " + "  x  " + "  1  " + "  x  " + "  x  " + "  x  " + "  x  " + "  x  " + "  1  " + "  x  " + " |\n"
            + "| " + "  x  " + "  x  " + "  x  " + "  x  " + "  x  " + "  x  " + "  x  " + "  x  " + "  x  " + "  x  " + " |\n"
            + "| " + "  x  " + "  x  " + "  x  " + "  x  " + "  x  " + "  x  " + "  x  " + "  x  " + "  x  " + "  x  " + " |\n"
            + "+-" + "-----" + "-----" + "-----" + "-----" + "-----" + "-----" + "-----" + "-----" + "-----" + "-----" + "-+\n"
        )
      }
    }
  }
}
