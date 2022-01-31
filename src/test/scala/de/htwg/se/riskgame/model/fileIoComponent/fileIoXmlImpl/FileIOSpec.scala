package de.htwg.se.riskgame.model.fileIoComponent.fileIoXmlImpl

import de.htwg.se.riskgame.controller.controllerComponent.controllerBasicImpl.Controller
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.deskCreatorComponent.deskCreatorBasicImpl.DeskCreateContinentMapStrategy
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.Field
import de.htwg.se.riskgame.model.deskComponent.DeskInterface
import de.htwg.se.riskgame.model.fileIoComponent.fileIoJsonImpl.MapData
import de.htwg.se.riskgame.model.teamComponent.Team
import de.htwg.se.riskgame.model.troopComponent.Troop
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FileIOSpec extends AnyWordSpec with Matchers {
  "A XML FileIO" when {
    "created" should {
      val fileIO = new FileIO
      val desk = new DeskCreateContinentMapStrategy().createDesk()
      val controller = new Controller(desk)
      controller.set(1, 0, Field("North America", Troop(3, Team.BLUE)))
      controller.set(1, 1, Field("Europe", Troop(3, Team.RED)))
      "have a save function" in {
        val unsavedDesk = controller.desk
        fileIO.save(controller.desk)
        unsavedDesk.equals(controller.desk) should be(true)
      }
      "have a load function" in {
        val savedDesk = controller.desk
        controller.desk.equals(savedDesk) should be(true)
        controller.set(1, 1, Field("Europe", Troop(3, Team.BLUE)))
        controller.desk.equals(savedDesk) should be(false)
        fileIO.load.isInstanceOf[DeskInterface] should be(true)
        // FURTHER TESTING NEEDED!
      }
    }
  }

}
