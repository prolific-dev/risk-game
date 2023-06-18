package de.htwg.se.riskgame.model.deskComponent.deskBasicImpl

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class MatrixSpec extends AnyWordSpec with Matchers:
    "A Matrix" when {
        "created by constructor" should {
            "have a size" in {

            }
        }
        "created by apply method" should {
            val matrix = new Matrix(3)
            "have a size" in {
                matrix.size shouldBe 3
            }

            "by default filled with blocked fields" in {
                val rows: Vector[Vector[Field]] = matrix.rows
                rows.flatten.forall(field => field.isInstanceOf[BlockedField]) shouldBe true
            }

            "return field by id" in {
                matrix.fieldById(1).isDefined shouldBe true
                matrix.fieldById(15) shouldBe None
            }

            "return field by coordinates" in {
                val field: Field = matrix.fieldByCoords(0, 0)
                field.isInstanceOf[Field] shouldBe true

            }

            "replace a field" in {
                val blockedField: Field = matrix.fieldByCoords(0, 0)
                blockedField.isInstanceOf[BlockedField] shouldBe true

                val newField  = Field("bridge", 1, 0, 0, matrix.size, "", 0)
                val newMatrix = matrix.replaceField(0, 0, newField)

                val bridgeField: Field = newMatrix.fieldByCoords(0, 0)
                bridgeField.isInstanceOf[BridgeField] shouldBe true

            }
        }
    }
