package de.htwg.se.riskgame.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class BlockedFieldSpec extends AnyWordSpec with Matchers {
  "An BlockedField" when {
    "new" should {
      val field = new BlockedField()
      "have no troops" in {
        field.getTroop() should be(None)
      }
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
