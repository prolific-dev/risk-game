package de.htwg.se.riskgame.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PlayerSpec extends AnyWordSpec with Matchers {
  "A Player" when {
    "new" should {
      val player = new Player("Your Name")
      "have a name" in {
        player.name should be("Your Name")
      }
      "have no team" in {
        player.team should be(Team.NO_TEAM)
      }
      "have a nice String representation" in {
        player.toString should be("Your Name")
      }
    }
    "only for class test purpose and 100% coverage" should {
      val player = new Player("Your Name")
      player.equals(Player("Your Name", Team.NO_TEAM))
    }
  }
}
