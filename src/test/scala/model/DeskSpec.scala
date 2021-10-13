package de.htwg.se.riskgame.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class DeskSpec extends AnyWordSpec with Matchers {
  "A Desk is the playingfield of Risk Game" when {
    "to be constructed" should {
      "be created with the length of its edges as size" in {
        val desk = new Desk(2)
        desk.size should be(2)
      }
      "for test purposes only created with a Matrix of Fields" in {
        val desk = new Desk(2)
        val matrixDesk = new Desk(new Matrix[Field](2, new IllegalField()))
        val vectorDesk = new Desk(new Matrix[Field](
          Vector(Vector(new IllegalField(), new IllegalField()), Vector(new IllegalField(), new IllegalField()))))
        desk should be(matrixDesk)
        desk should be(vectorDesk)
      }
    }
    "created properly but empty" should {
      val desk = new Desk(2)
      "give acces to its Fields" in {
        desk.field(0, 0) should be(new IllegalField())
        desk.field(0, 1) should be(new IllegalField())
        desk.field(1, 0) should be(new IllegalField())
        desk.field(1, 1) should be(new IllegalField())
      }
      "allow to set individual Fields and remain immutable" in {
        val changedDesk = desk.set(0, 0, new LegalField("LegalField"))
        desk.field(0, 0) should be(IllegalField())
      }
      "change the colorization of the desk and each field depending on their occupying team" in {
        desk.isColorized should be(false)
        val deskColorOn = desk.setColorizedOn()
        deskColorOn.isColorized should be(true)
        val deskColorOff = desk.setColorizedOff()
        deskColorOff.isColorized should be(false)
      }
      "have a nice String representation" in {
        val desk = new Desk(2)
        desk.toString should be(
          "\n"
            + "+-" + "-----" + "-----" + "-+\n"
            + "| " + "  x  " + "  x  " + " |\n"
            + "| " + "  x  " + "  x  " + " |\n"
            + "+-" + "-----" + "-----" + "-+\n"
        )
      }
    }
    "not empty" should {
      val desk = new Desk(2)
      val changedDesk = desk.set(1, 1, new LegalField("LegalField", new Troop(1, BLUE_TEAM)))
      "have a nice String representation" in {
        changedDesk.toString should be(
          "\n"
            + "+-" + "-----" + "-----" + "-+\n"
            + "| " + "  x  " + "  x  " + " |\n"
            + "| " + "  x  " + "  1  " + " |\n"
            + "+-" + "-----" + "-----" + "-+\n"
        )
      }
      "have a nice colorized String representation" in {
        val colorizedDesk = changedDesk.setColorizedOn()
        val ansiBlue = "\u001B[34m"
        val ansiNormal = "\u001B[0m"
        val string = colorizedDesk.toString

        colorizedDesk.toString should be(
          "\n"
            + "+-" + "-----" + "-----" + "-+\n"
            + "|   " + "x" + ansiNormal + "    " + "x" + ansiNormal + "   |\n"
            + "|   " + "x" + ansiNormal + "    " + ansiBlue + "1" + ansiNormal + "   |\n"
            + "+-" + "-----" + "-----" + "-+\n"
        )
      }
    }
  }
}
