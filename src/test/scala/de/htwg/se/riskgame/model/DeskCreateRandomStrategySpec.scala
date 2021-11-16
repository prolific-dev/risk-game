package de.htwg.se.riskgame.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class DeskCreateRandomStrategySpec extends AnyWordSpec with Matchers {
  "A DeskCreateRandomStrategy" when {
    "create" should {
      val desk = new DeskCreateRandomStrategy().createDesk(3)
      "empty Desk and fill it with cells with a creation strategy" in {
        desk.size should be(3)
      }
      "have a certain amount of BlockedFields and be valid" in {
        var countBlockedFields = 0
        desk.fields.rows
          .foreach(vector =>
            vector.foreach(field => {
              if (field.isInstanceOf[BlockedField]) {
                countBlockedFields += 1
              }
            }))
        countBlockedFields <= desk.size * 2 should be(true)
        desk.valid() should be(true)
      }
    }
    "for trait test purpose only" should {
      val strategy = new DeskCreateStrategyTemplate {}
      val desk = strategy.createDesk(3)
      desk.size should be(3)
      desk.valid() should be(true)
    }
  }
}
