package de.htwg.se.riskgame.controller

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GameStatusSpec extends AnyWordSpec with Matchers {
  "A GameStatus" when {
    "called" should {
      val idle = GameStatus.IDLE
      val won = GameStatus.WON
      val lost = GameStatus.LOST

      "have a message" in {
        idle.message should be("")
        won.message should be("Player won the game")
        lost.message should be("Player lost the game")
      }
    }
  }

}
