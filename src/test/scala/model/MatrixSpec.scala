package de.htwg.se.riskgame.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MatrixSpec extends AnyWordSpec with Matchers {
  "A Matrix data type that contains two-dimentional Vector of Fields" when {
    "empty" should {
      "be created by using a dimention and a sample field" in {
        val matrix = new Matrix[Field](2, Field("Field"))
        matrix.size should be(2)
      }
      "for test purposes only be created with a Vector of Vectors" in {
        val testMatrix = Matrix(Vector(Vector(Field("Field"))))
        testMatrix.size should be(1)
      }
    }
    "filled" should {
      val matrix = new Matrix[Field](2, Field("Field"))
      "give access to its fields" in {
        matrix.field(0, 0) should be(Field("Field"))
      }
      "replace fields and return a new data structure" in {
        val returnedMatrix = matrix.replaceField(0, 0, Field("Replaced Field"))
        returnedMatrix.field(0, 0) should be(Field("Replaced Field"))
      }
      "be filled using fill operation" in {
        val returnedMatrix = matrix.fill(Field("Alternate Fields"))
        returnedMatrix.field(0, 0) should be(Field("Alternate Fields"))
      }
    }
  }
}
