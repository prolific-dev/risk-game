package de.htwg.se.riskgame.controller

import de.htwg.se.riskgame.model.{Desk, OccupiedField, Team, Troop}
import de.htwg.se.riskgame.util.Observer
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.language.reflectiveCalls

class ControllerSpec extends AnyWordSpec with Matchers {
  "A controller" when {
    "observed by an Observer" should {
      var updated = false
      val desk = new Desk(3)
      val controller = new Controller(desk)
      val observer = new Observer {
        override def update: Unit = updated = true
      }

      controller.add(observer)

      "notify its Observer after creation" in {
        controller.createEmptyDesk(3)
        updated should be(true)
        controller.desk.size should be(3)
        updated = false // reset for further tests
      }
      "notify its Observer after random creation" in {
        controller.createRandomDesk(3, Seq(Team.BLUE), 1)
        updated should be(true)
        controller.desk.valid() should be(true)
        updated = false // reset for further tests
      }
      "notify its Observer after setting a field" in {
        controller.set(0, 0, new OccupiedField("", new Troop(3, Team.BLUE)))
        updated should be(true)
        controller.desk.field(0, 0) should be(OccupiedField("", Troop(3, Team.BLUE)))
        updated = false // reset for further tests
      }
      "notify its Observer after colorization gets set on" in {
        controller.setColorizedOn
        updated should be(true)
        controller.desk.isColorized should be(true)
        updated = false // reset for further tests
      }
      "notify its Observer after colorization gets set off" in {
        controller.setColorizedOff
        updated should be(true)
        controller.desk.isColorized should be(false)
        updated = false // reset for further tests
      }
    }
    "empty" should {
      val desk = new Desk(3)
      val controller = new Controller(desk)
      "handle undo/redo correctly on an empty undo-stack" in {
        
      }
    }
  }
}
