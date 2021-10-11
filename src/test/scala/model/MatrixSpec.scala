package de.htwg.se.riskgame.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MatrixSpec extends AnyWordSpec with Matchers {
  "A Matrix data type that contains two-dimentional Vector of Fields" when {
    "empty" should {
      "be created by using a dimention and a sample field" in {
        val matrix = new Matrix[Field](2, IllegalField())
        matrix.size should be(2)
      }
      "for test purposes only be created with a Vector of Vectors" in {
        val testMatrix = Matrix(Vector(Vector(IllegalField())))
        testMatrix.size should be(1)
      }
    }
    "filled" should {
      val matrix = new Matrix[Field](2, IllegalField())
      "give access to its fields" in {
        matrix.field(0, 0) should be(IllegalField())
      }
      "replace fields and return a new data structure" in {
        val returnedMatrix = matrix.replaceField(0, 0, LegalField("Replaced Field"))
        returnedMatrix.field(0, 0) should be(LegalField("Replaced Field"))
      }
      "be filled using fill operation" in {
        val returnedMatrix = matrix.fill(LegalField("Alternate Fields"))
        returnedMatrix.field(0, 0) should be(LegalField("Alternate Fields"))
      }
    }
  }
}
