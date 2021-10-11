package de.htwg.se.riskgame.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class LegalFieldSpec extends AnyWordSpec with Matchers {
  "A LegalField" when {
    "new" should {
      val field = LegalField("LegalField")
      "have a nice String representation" in {
        field.toString() should be("LegalField")
      }
    }
  }
}
