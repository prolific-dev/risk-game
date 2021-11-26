package de.htwg.se.riskgame

import de.htwg.se.riskgame.aview.*
import de.htwg.se.riskgame.controller.Controller
import de.htwg.se.riskgame.model.*
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class RiskGameSpec extends AnyWordSpec with Matchers {
  "A RiskGame" when {
    "by default" should {
      "have a controller and a tui" in {
        RiskGame.controller.isInstanceOf[Controller] should be(true)
        RiskGame.tui.isInstanceOf[Tui] should be(true)
      }
    }
  }
}
