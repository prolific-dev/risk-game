package de.htwg.se.riskgame.model.fileIoComponent.fileIoJsonImpl

import de.htwg.se.riskgame.controller.controllerComponent.controllerBasicImpl.Controller
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.deskCreatorComponent.deskCreatorBasicImpl.DeskCreateContinentMapStrategy
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.Field
import de.htwg.se.riskgame.model.deskComponent.DeskInterface
import de.htwg.se.riskgame.model.fileIoComponent.fileIoJsonImpl.FileIO
import de.htwg.se.riskgame.model.teamComponent.Team
import de.htwg.se.riskgame.model.troopComponent.Troop
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FileIOSpec extends AnyWordSpec with Matchers {
  "A JSON FileIO" when {
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
      "have a load function [FURTHER TESTING NEEDED!]" in {
        val savedDesk = controller.desk
        controller.desk.equals(savedDesk) should be(true)
        controller.set(1, 1, Field("Europe", Troop(3, Team.BLUE)))
        controller.desk.equals(savedDesk) should be(false)
        fileIO.load.isInstanceOf[DeskInterface] should be(true)
        
      }
      "have a loadGuiMapDataPath function" in {
        val mapData = fileIO.loadGuiMapDataPath()
        mapData.isInstanceOf[Map[String, MapData]] should be(true)
        mapData("North America").equals(MapData("m 134.97183,175.24652 c 4.80198,0.87778 5.78303,2.47844 5.78303,2.47844 0,0 1.08432,3.71766 -1.60066,2.68498 -2.68498,-1.03268 -7.74513,-1.75556 -5.98957,-3.45949 1.75557,-1.70393 1.8072,-1.70393 1.8072,-1.70393 z m -19.25956,-5.06016 c 10.27521,0.67125 12.44385,2.94315 12.44385,2.94315 0,0 2.22027,-0.6196 2.94314,0.51635 0.72289,1.13595 1.3425,5.31832 -2.68497,2.89151 -4.02747,-2.4268 -9.8105,-3.66603 -11.10136,-3.76929 -1.29085,-0.10327 -3.9242,-1.03269 -2.73661,-1.8072 1.18758,-0.77452 1.13595,-0.77452 1.13595,-0.77452 z M 39.655075,117.10639 c 24.37135,-16.93602 40.481223,-14.25104 40.481223,-14.25104 0,0 38.415852,8.88108 47.503472,5.36996 9.08762,-3.51113 28.50209,-7.64186 23.95828,-1.85883 -4.54381,5.78303 -27.26287,9.08762 -25.40403,14.0445 1.85883,4.95689 9.08762,13.21836 13.42489,8.88109 4.33727,-4.33727 5.78303,-16.72949 12.59875,-15.28373 6.81571,1.44576 25.81711,7.64187 18.58832,12.80529 -7.22879,5.16342 -45.02504,25.8171 -46.05772,30.36091 -1.03269,4.54381 3.71766,14.45758 -1.44576,9.5007 -5.16342,-4.95689 -18.38178,-10.94646 -22.71905,-4.54381 -4.337276,6.40264 -9.087624,15.28372 -0.826152,13.83797 8.261472,-1.44576 10.739912,-7.43533 11.772602,-2.47845 1.03268,4.95689 14.87065,15.49027 10.12031,17.3491 -4.75035,1.85883 -32.632829,-9.70723 -35.73088,-13.21836 -3.098054,-3.51112 -27.469401,-28.08901 -20.447148,-34.90473 7.022253,-6.81571 19.001391,-15.6968 9.500695,-20.24061 -9.500695,-4.54381 -33.458969,9.29416 -35.317802,2.68498 -1.85883,-6.60918 0,-8.05494 0,-8.05494 z", 65, 60)) should be(true)
      }
    }
  }
}
