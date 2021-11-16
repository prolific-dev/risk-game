package de.htwg.se.riskgame.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class DeskCreateRandomStrategySpec extends AnyWordSpec with Matchers {
  "A DeskCreateRandomStrategy" should {
    "create an empty Desk and fill it with cells with a creation strategy" in {
      val desk = new DeskCreateRandomStrategy().createDesk(1)
      desk.size should be(1)
    }
  }
}
