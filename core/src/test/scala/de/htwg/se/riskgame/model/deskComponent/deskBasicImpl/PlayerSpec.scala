package de.htwg.se.riskgame.model.deskComponent.deskBasicImpl

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class PlayerSpec extends AnyWordSpec with Matchers {

    "A Player" when {
        "created by constructor" should {
            val player = Player(1, "PlayerName", Team.Blue)
            "have an id" in {
                player.id shouldBe 1
            }
            "have a name" in {
                player.name shouldBe "PlayerName"

            }
            "have a team" in {
                player.team shouldBe Team.Blue

            }
        }
        "created by apply method" should {
            val p1 = new Player(1)
            val p2 = new Player(2)
            val p3 = new Player(3)
            val p4 = new Player(4)
            val p5 = new Player(5)

            "have an id" in {
                p1.id shouldBe 1
                p2.id shouldBe 2
                p3.id shouldBe 3
                p4.id shouldBe 4
                p5.id shouldBe 5
            }

            "have a name" in {
                p1.name shouldBe "P1"
                p2.name shouldBe "P2"
                p3.name shouldBe "P3"
                p4.name shouldBe "P4"
                p5.name shouldBe "P5"
            }

            "have a team" in {
                p1.team shouldBe Team.Blue
                p2.team shouldBe Team.Red
                p3.team shouldBe Team.Green
                p4.team shouldBe Team.Yellow
                p5.team shouldBe Team.NoTeam
            }
        }
    }

}
