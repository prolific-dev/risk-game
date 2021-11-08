package de.htwg.se.riskgame.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TeamSpec extends AnyWordSpec with Matchers {
  "A Team" when {
    "called" should {
      val noteam = Team.NO_TEAM
      val blueteam = Team.BLUE
      val redteam = Team.RED

      "have an order" in {
        noteam.getOrder should be(0)
        blueteam.getOrder should be(1)
        redteam.getOrder should be(2)
      }
      "have a ansi color representated as formatted string" in {
        noteam.getAnsi should be("")
        blueteam.getAnsi should be("\u001b[34m")
        redteam.getAnsi should be("\u001b[31m")
      }
      "have a nice String representation" in {
        noteam.getName should be("No Team")
        blueteam.getName should be("Blue")
        redteam.getName should be("Red")
      }
    }
  }
}
