package de.htwg.se.riskgame.controller

import de.htwg.se.riskgame.model.{Desk, LegalField, Team, Troop}
import de.htwg.se.riskgame.util.Observer
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ControllerSpec extends AnyWordSpec with Matchers {
  "A controller" when {
    "observed by an Observer" should {
      val desk = new Desk(3)
      val controller = new Controller(desk)
      val observer = new Observer {
        var updated: Boolean = false

        def isUpdated: Boolean = updated

        override def update: Unit = {
          updated = true; updated
        }
      }
      controller.add(observer)
      "notify its Observer after creation" in {
        controller.createEmptyDesk(3)
        //observer.updated should be(true)
        controller.desk.size should be(3)
      }
      "notify its Observer after random creation" in {
        controller.createRandomDesk(3, Seq(Team.BLUE), 1)
        //observer.updated should be(true)
        controller.desk.size should be(3)
      }
      "notify its Observer after setting a field" in {
        controller.set(0, 0, new LegalField("", new Troop(3, Team.BLUE)))
        //observer.updated should be(true)
        controller.desk.size should be(3)
      }
      "notify its Observer after colorization gets set on" in {
        controller.setColorizedOn
        //observer.updated should be(true)
        controller.desk.isColorized should be(true)
      }
      "notify its Observer after colorization gets set off" in {
        controller.setColorizedOff
        //observer.updated should be(true)
        controller.desk.isColorized should be(false)
      }
    }
  }
}
