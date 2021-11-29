package de.htwg.se.riskgame
package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class DeskSpec extends AnyWordSpec with Matchers {
  "A Desk is the playingfield of Risk Game" when {
    "to be constructed" should {
      "be created with the length of its edges as size and a default team-set and playersTurn" in {
        val desk = new Desk(2)
        desk.size should be(2)
        desk.info should be(DeskInfo(IndexedSeq[Team](Team.BLUE, Team.RED), 0))
      }
      "for test purposes only created with a Matrix of Fields" in {
        val desk = new Desk(2)
        val matrixDesk = new Desk(new Matrix[Field](2, Field("free")), new DeskInfo)
        val vectorDesk = new Desk(new Matrix[Field](
          Vector(Vector(Field("free"), Field("free")), Vector(Field("free"), Field("free")))), new DeskInfo)
        desk should be(matrixDesk)
        desk should be(vectorDesk)
      }
    }
    "created properly but empty" should {
      val desk = new Desk(2)
      "give acces to its Fields" in {
        desk.field(0, 0) should be(new OccupiedField())
        desk.field(0, 1) should be(new OccupiedField())
        desk.field(1, 0) should be(new OccupiedField())
        desk.field(1, 1) should be(new OccupiedField())
      }
      "allow to set individual Fields and remain immutable" in {
        val changedDesk = desk.set(0, 0, new OccupiedField("OccupiedField"))
        desk.field(0, 0) should be(new OccupiedField())
      }
      "give out a neighbors datastructure of a field with a neighborMap" in {
        val desk = new Desk(3)
        desk.neighbors(1, 1) should be(Neighbors(1, 1, desk.fields))
        desk.neighbors(1, 1).neighborMap should be(
          Map(
            "N" -> Some(desk.field(0, 1)),
            "NE" -> Some(desk.field(0, 2)),
            "E" -> Some(desk.field(1, 2)),
            "SE" -> Some(desk.field(2, 2)),
            "S" -> Some(desk.field(2, 1)),
            "SW" -> Some(desk.field(2, 0)),
            "W" -> Some(desk.field(1, 0)),
            "NW" -> Some(desk.field(0, 0))
          )
        )
      }
      "return all current playing teams within a IndexedSeq" in {
        desk.teams should be(IndexedSeq[Team](Team.BLUE, Team.RED))
      }
      "return the team of the current turn" in {
        desk.currentPlayerTurn should be(Team.BLUE)
      }
      "increment the playerTurn value after an ended turn" in {
        var nextPlayerTurnDesk = desk
        nextPlayerTurnDesk = nextPlayerTurnDesk.endTurn
        nextPlayerTurnDesk.currentPlayerTurn should be(Team.RED)
        nextPlayerTurnDesk = nextPlayerTurnDesk.endTurn
        nextPlayerTurnDesk.currentPlayerTurn should be(Team.BLUE)
      }
      "state valid status depending on if every of its point has at least one regular field neighbor" in {
        val desk = new Desk(3)

        desk.valid should be(true)

        val invalidDesk = new Desk(desk.fields.fill(BlockedField()).replaceField(1, 1, new OccupiedField("")), new DeskInfo)

        invalidDesk.valid should be(false)

      }
      "have a nice String representation" in {
        val desk = new Desk(2)
        desk.toString should be(
          "\n"
            + "+-" + "-----" + "-----" + "-+\n"
            + "| " + "  1  " + "  1  " + " |\n"
            + "| " + "  1  " + "  1  " + " |\n"
            + "+-" + "-----" + "-----" + "-+\n"
        )
      }
    }
    "not empty" should {
      val desk = new Desk(2)
      val changedDesk = desk.set(1, 1, new OccupiedField("OccupiedField", new Troop(3, Team.BLUE)))
      val ansiBlue = "\u001B[34m"
      val ansiNormal = "\u001B[0m"
      "have a nice String representation" in {
        changedDesk.toString should be(
          "\n"
            + "+-" + "-----" + "-----" + "-+\n"
            + "| " + "  1  " + "  1  " + " |\n"
            + "| " + "  1  " + "  " + ansiBlue + "3" + ansiNormal + "   |\n"
            + "+-" + "-----" + "-----" + "-+\n"
        )
      }
    }
    "only for class test purpose and 100% coverage" should {
      val desk = new Desk(1)
      val matrix = desk.fields
      desk.equals(new Desk(matrix.size)) should be(true)
    }
  }
}
