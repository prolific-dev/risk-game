package de.htwg.se.riskgame.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class IllegalFieldSpec extends AnyWordSpec with Matchers {
  "An IllegalField" when {
    "new" should {
      val field = IllegalField()
      "have a nice String representation" in {
        field.toString should be("x")
      }
    }
  }
}
