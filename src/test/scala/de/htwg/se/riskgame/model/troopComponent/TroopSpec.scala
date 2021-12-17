package de.htwg.se.riskgame.model.troopComponent

import de.htwg.se.riskgame.model.teamComponent.Team
import de.htwg.se.riskgame.model.troopComponent.Troop
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TroopSpec extends AnyWordSpec with Matchers {
  "A troop" when {
    "new" should {
      val troop = new Troop(3)
      "return amount of troops and have no team" in {
        troop.amount should be(3)
        troop.team should be(Team.NO_TEAM)
      }
      "have a nice String representation" in {
        troop.toString should be("3")
      }
    }
  }
}
