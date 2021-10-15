package de.htwg.se.riskgame.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class LegalFieldSpec extends AnyWordSpec with Matchers {
  "A LegalField" when {
    "new" should {
      val field = new LegalField("LegalField", new Troop(1))
      "have no team" in {
        field.team() should be(Team.NO_TEAM)
      }
      "should not be set" in {
        field.isSet() should be(false)
      }
      "have a nice String representation" in {
        field.toString() should be("1")
      }
    }
  }
}
