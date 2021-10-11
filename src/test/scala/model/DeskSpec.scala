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
        val matrixDesk = Desk(new Matrix[Field](2, Field("x")))
        val vectorDesk = Desk(Matrix[Field](Vector(Vector(Field("x"), Field("x")), Vector(Field("x"), Field("x")))))
        desk should be(matrixDesk)
        desk should be(vectorDesk)
      }
    }
    "created properly but empty" should {
      val desk = new Desk(2)
      "give acces to its Fields" in {
        desk.field(0, 0) should be(Field("x"))
        desk.field(0, 1) should be(Field("x"))
        desk.field(1, 0) should be(Field("x"))
        desk.field(1, 1) should be(Field("x"))
      }
      "allow to set individual Fields and remain immutable" in {
        val changedDesk = desk.set(0, 0, "Field")
        desk.field(0, 0) should be(Field("x"))
      }
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

}
