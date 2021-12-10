package de.htwg.se.riskgame.controller

import de.htwg.se.riskgame.model.{Desk, Field, Team, Troop}
import de.htwg.se.riskgame.util.Observer
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.io.AnsiColor
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
        controller.createRandomDesk(3)
        updated should be(true)
        controller.desk.valid should be(true)
        updated = false // reset for further tests
      }
      "notify its Observer after worldmap creation" in {
        controller.createWorldMapDesk()
        updated should be(true)
        controller.desk.valid should be(true)
        updated = false // reset for further tests
      }
      "notify its Observer after setting a field" in {
        controller.set(0, 0, Field("x"))
        updated should be(true)
        controller.desk.field(0, 0).getName should be("x")
        controller.desk.field(0, 0).getTroop should be(None)
        updated = false // reset for further tests
      }
    }
    "empty" should {
      val desk = new Desk(3)
      val controller = new Controller(desk)

      "handle undo/redo correctly on an empty undo-stack" in {
        controller.desk.field(0, 0).isSet should be(false)
        controller.undo()
        controller.desk.field(0, 0).isSet should be(false)
        controller.redo()
        controller.desk.field(0, 0).isSet should be(false)
      }
      "handle undo/redo of setting a field correctly" in {
        controller.desk.field(0, 0).isSet should be(false)
        controller.desk.field(0, 0).getTroop should be(Some(Troop(1, Team.NO_TEAM)))
        controller.set(0, 0, Field("Occupied Field", Troop(3, Team.BLUE)))
        controller.desk.field(0, 0).isSet should be(true)
        controller.desk.field(0, 0).getTroop should be(Some(Troop(3, Team.BLUE)))
        controller.undo()
        controller.desk.field(0, 0).isSet should be(false)
        controller.desk.field(0, 0).getTroop should be(Some(Troop(1, Team.NO_TEAM)))
        controller.redo()
        controller.desk.field(0, 0).isSet should be(true)
        controller.desk.field(0, 0).getTroop should be(Some(Troop(3, Team.BLUE)))
      }

    }
    "not empty" should {
      val desk = new Desk(3)
      val controller = new Controller(desk)
      controller.set(1, 1, Field("Blue Field", Troop(3, Team.BLUE)))

      "set highlight of friendly neighbors" in {
        controller.set(0, 2, Field("Blue Field", Troop(3, Team.BLUE)))
        controller.chooseAndShowFriendlies(1, 1)
        controller.desk.field(0, 2).toString should be(AnsiColor.YELLOW + "3" + AnsiColor.RESET)
      }

      "set highlight of enemy neighbors" in {
        controller.set(2, 0, Field("Red Field", Troop(3, Team.RED)))
        controller.chooseAndShowEnemies(1, 1)
        controller.desk.field(2, 0).toString should be(AnsiColor.YELLOW + "3" + AnsiColor.RESET)
      }
    }
    "have always a GameStatus" should {
      val desk = new Desk(3)
      val controller = new Controller(desk)

      "change GameStatus properly" in {
        controller.gameStatus should be(GameStatus.IDLE)
        controller.createEmptyDesk(3)
        controller.gameStatus should be(GameStatus.EMPTY)
        controller.createRandomDesk(3)
        controller.gameStatus should be(GameStatus.NEW)
        controller.createWorldMapDesk()
        controller.gameStatus should be(GameStatus.NEW)
        controller.set(0, 0, Field("x"))
        controller.gameStatus should be(GameStatus.SET)
        controller.undo()
        controller.gameStatus should be(GameStatus.UNDO)
        controller.redo()
        controller.gameStatus should be(GameStatus.REDO)
      }
    }
  }
}
