package de.htwg.se.riskgame.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class DeskCreateRandomStrategySpec extends AnyWordSpec with Matchers {
  "A DeskCreateRandomStrategy" should {
    "create an empty Desk and fill it with fields with a creation strategy" in {
      val desk = new DeskCreateRandomStrategy(1, Seq(Team.BLUE)).createRandom(1)
      val field = desk.field(0, 0)

      field.isInstanceOf[Field] should be(true)

      if (field.isInstanceOf[OccupiedField]) {
        field.team() should be(Team.BLUE)
        field.getTroop() should be(Some(new Troop(3, Team.BLUE)))
      }
    }
    "not set an already set field and return the putted desk as it was before (num = 2)" in {
      val desk = new DeskCreateRandomStrategy(1, Seq(Team.BLUE)).createRandom(2)
      val field = desk.field(0, 0)

      field.isInstanceOf[OccupiedField] should be(true)
      field.team() should be(Team.BLUE)
      field.getTroop() should be(Some(new Troop(3, Team.BLUE)))
    }
  }
}
