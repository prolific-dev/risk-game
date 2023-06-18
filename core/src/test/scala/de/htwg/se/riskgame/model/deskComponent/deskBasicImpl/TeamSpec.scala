package de.htwg.se.riskgame.model.deskComponent.deskBasicImpl

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class TeamSpec extends AnyWordSpec with Matchers:

    "A team" when {
        "specified" should {
            val noteam = Team.NoTeam
            val blue   = Team.Blue
            val red    = Team.Red
            val green  = Team.Green
            val yellow = Team.Yellow

            "have an id" in {
                noteam.getId shouldBe 0
                blue.getId   shouldBe 1
                red.getId    shouldBe 2
                green.getId  shouldBe 3
                yellow.getId shouldBe 4
            }

            "have a rgb color" in {
                noteam.getRgb shouldBe 0xFFFFFF
                blue.getRgb   shouldBe 0x0000FF
                red.getRgb    shouldBe 0xFF0000
                green.getRgb  shouldBe 0x00FF00
                yellow.getRgb shouldBe 0xFFFF00
            }
        }
    }
