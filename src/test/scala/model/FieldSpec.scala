package de.htwg.se.riskgame.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FieldSpec extends AnyWordSpec with Matchers {
  "A Field" when {
    "new" should {
      val field = Field("Field")
      "have a nice String representation" in {
        field.toString() should be("Field")
      }
    }
  }
}
