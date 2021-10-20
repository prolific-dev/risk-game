package de.htwg.se.riskgame.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class NeighborsSpec extends AnyWordSpec with Matchers {
  "A Neighbors" when {
    "created" should {
      val fields = new Matrix[IField](3, new Field(""))
      "keep a fields' neighbors inside a map" in {
        val neighbor = new Neighbors(1, 1, fields)
        neighbor.map should be(Map(
          "N" -> Some(new Field("")),
          "S" -> Some(new Field("")),
          "W" -> Some(new Field("")),
          "E" -> Some(new Field(""))
        ))
      }
      "mark neighbors out of the bounds as None" in {
        val edgeNeighbors = new Neighbors(0, 0, fields)
        edgeNeighbors.map should be(Map(
          "N" -> None,
          "S" -> Some(new Field("")),
          "W" -> None,
          "E" -> Some(new Field(""))
        ))

        val otherEdgeNeighbors = new Neighbors(2, 2, fields)
        otherEdgeNeighbors.map should be(Map(
          "N" -> Some(new Field("")),
          "S" -> None,
          "W" -> Some(new Field("")),
          "E" -> None
        ))
      }
      "return its center position and the field all neighbors are depending on" in {
        val changedMatrix = fields.replaceField(1, 1, new Field("Center Field"))
        val neighbors = new Neighbors(1, 1, changedMatrix)

        neighbors.center() should be(Field("Center Field", Troop(1, Team.NO_TEAM)))
      }
      "state if its a valid neighbor map or not. It is only valid if the map contains at least on type of Field" in {
        val allBlockedFields = fields.fill(new BlockedField())
        val neighbors = new Neighbors(1, 1, allBlockedFields)

        neighbors.valid() should be(false)

        val atleastOneField = allBlockedFields.replaceField(0, 1, new Field(""))
        val validNeighbors = new Neighbors(1, 1, atleastOneField)

        validNeighbors.valid() should be(true)
      }
    }
  }
}
