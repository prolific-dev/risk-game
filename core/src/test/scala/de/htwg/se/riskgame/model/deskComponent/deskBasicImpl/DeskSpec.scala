package de.htwg.se.riskgame.model.deskComponent.deskBasicImpl

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.riskgame.model.mapCreatorComponent.mapCreatorDefault.MapCreator
import de.htwg.se.riskgame.model.deskComponent.DeskInterface
import de.htwg.se.riskgame.model.mapCreatorComponent.MapCreatorTemplate

class DeskSpec extends AnyWordSpec with Matchers:
    "A Desk" when {
        "created with a size" should {
            val desk = new Desk(2)
            
            "have fields" in {
                desk.fields shouldEqual new Matrix(2)
            }

            "have an info" in {
                desk.info shouldEqual new Info()
            }
            "have a size" in {
                desk.size shouldBe 2
            }
            "have a field accessor" in {
                desk.fieldByCoords(0, 0) shouldEqual Field("blocked", 1, 0, 0, 2, "", 0)
                desk.fieldById(1)        shouldEqual Some(Field("blocked", 1, 0, 0, 2, "", 0))
            }
            "have a replace field function" in {
                val mapCreator: MapCreatorTemplate = new MapCreator
                var areaDesk = new Desk(mapCreator.createMap)

                areaDesk.fieldByCoords(0, 0) shouldEqual Field("area", 1, 0, 0, 3, "", 0)
                areaDesk.fieldById(1)        shouldEqual Some(Field("area", 1, 0, 0, 3, "", 0))

                areaDesk = areaDesk.replaceFieldByCoords(0, 0, 3, Team.Blue) 
                areaDesk.fieldByCoords(0, 0) shouldEqual Field(1, 0, 0, 3, 3, Some(Team.Blue), "", 0)

                areaDesk = areaDesk.replaceFieldById(1, 1, Team.Red)
                areaDesk.fieldById(1)        shouldEqual Some(Field(1, 0, 0, 3, 1, Some(Team.Red), "", 0))
            }
            "have a select field function" in {
                val mapCreator: MapCreatorTemplate = new MapCreator
                var areaDesk = new Desk(mapCreator.createMap)

                areaDesk = areaDesk.selectFieldByCoords(0, 0)
                areaDesk = areaDesk.selectFieldById(2)

                areaDesk.info.selectedFieldPrimary   shouldEqual Some(Field("area", 1, 0, 0, 3, "", 0))
                areaDesk.info.selectedFieldSecondary shouldEqual Some(Field("area", 2, 0, 1, 3, "", 0))
            }
            "have a string format" in {

            }
        }
    }
