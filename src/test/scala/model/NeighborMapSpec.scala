package de.htwg.se.riskgame.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class NeighborMapSpec extends AnyWordSpec with Matchers {
  "A NeighborMap" when {
    "created" should {
      val fields = new Matrix[IField](3, new Field(""))
      "keep a fields' neighbors inside a map" in {
        val neighborMap = new NeighborMap(1, 1, fields)
        neighborMap.mapNeighbors() should be(Map(
          "N" -> Some(new Field("")),
          "S" -> Some(new Field("")),
          "W" -> Some(new Field("")),
          "E" -> Some(new Field(""))
        ))
      }
      "mark neighbors out of the bounds as None" in {
        val edgeNeighborsMap = new NeighborMap(0, 0, fields)
        edgeNeighborsMap.mapNeighbors() should be(Map(
          "N" -> None,
          "S" -> Some(new Field("")),
          "W" -> None,
          "E" -> Some(new Field(""))
        ))

        val otherEdgeNeighborsMap = new NeighborMap(2, 2, fields)
        otherEdgeNeighborsMap.mapNeighbors() should be(Map(
          "N" -> Some(new Field("")),
          "S" -> None,
          "W" -> Some(new Field("")),
          "E" -> None
        ))
      }
      "return its center position and the field all neighbors are depending on" in {
        val changedMatrix = fields.replaceField(1, 1, new Field("Center Field"))
        val neighborMap = new NeighborMap(1, 1, changedMatrix)

        neighborMap.center() should be(Field("Center Field", Troop(1, Team.NO_TEAM)))

      }
    }
  }
}
