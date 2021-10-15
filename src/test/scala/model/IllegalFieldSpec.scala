package de.htwg.se.riskgame.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class IllegalFieldSpec extends AnyWordSpec with Matchers {
  "An IllegalField" when {
    "new" should {
      val field = new IllegalField()
      "have no team" in {
        field.team() should be(Team.NO_TEAM)
      }
      "should not be set" in {
        field.isSet() should be(true)
      }
      "have a nice String representation" in {
        field.toString should be("x")
      }
    }
  }
}
