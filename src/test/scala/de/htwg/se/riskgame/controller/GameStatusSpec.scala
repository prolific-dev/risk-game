package de.htwg.se.riskgame.controller

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GameStatusSpec extends AnyWordSpec with Matchers {
  "A GameStatus" when {
    "called" should {
      val idle = GameStatus.IDLE
      val empty = GameStatus.EMPTY
      val _new = GameStatus.NEW
      val set = GameStatus.SET
      val undo = GameStatus.UNDO
      val redo = GameStatus.REDO
      val won = GameStatus.WON
      val lost = GameStatus.LOST

      "have a message" in {
        idle.message should be("")
        empty.message should be("A new empty game was created")
        _new.message should be("A new game was created")
        set.message should be("A field was set")
        undo.message should be("Undone one step")
        redo.message should be("Redone one step")
        won.message should be("Player won the game")
        lost.message should be("Player lost the game")
      }
    }
  }

}
