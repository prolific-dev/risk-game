package de.htwg.se.riskgame.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TeamSpec extends AnyWordSpec with Matchers {
  "A Team" when {
    "called" should {
      val unknown = UNKNOWN_TEAM
      val blueteam = BLUE_TEAM
      val redteam = RED_TEAM
      "have an order" in {
        unknown.getOrder should be(0)
        blueteam.getOrder should be(1)
        redteam.getOrder should be(2)
      }
      "have a nice String representation" in {
        unknown.getName should be("Unknown")
        blueteam.getName should be("Blue")
        redteam.getName should be("Red")
      }
    }
  }
}
