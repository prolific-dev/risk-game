package de.htwg.se.riskgame.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TeamSpec extends AnyWordSpec with Matchers {
  "A Team" when {
    "called" should {
      val noteam = NO_TEAM
      val blueteam = BLUE_TEAM
      val redteam = RED_TEAM
      "have an order" in {
        noteam.getOrder should be(0)
        blueteam.getOrder should be(1)
        redteam.getOrder should be(2)
      }
      "have a nice String representation" in {
        noteam.getName should be("No Team")
        blueteam.getName should be("Blue")
        redteam.getName should be("Red")
      }
    }
  }
}
