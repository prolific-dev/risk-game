package de.htwg.se.riskgame.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class NeighborsSpec extends AnyWordSpec with Matchers {
  "A Neighbors" when {
    "created" should {
      val fields = new Matrix[Field](3, Field("free"))
      "keep a fields' neighbors inside a neighborMap" in {
        val neighbor = new Neighbors(1, 1, fields)
        neighbor.neighborMap should be(Map(
          "N" -> Some(OccupiedField("Free Field", Troop(1, Team.NO_TEAM))),
          "NE" -> Some(OccupiedField("Free Field", Troop(1, Team.NO_TEAM))),
          "E" -> Some(OccupiedField("Free Field", Troop(1, Team.NO_TEAM))),
          "SE" -> Some(OccupiedField("Free Field", Troop(1, Team.NO_TEAM))),
          "S" -> Some(OccupiedField("Free Field", Troop(1, Team.NO_TEAM))),
          "SW" -> Some(OccupiedField("Free Field", Troop(1, Team.NO_TEAM))),
          "W" -> Some(OccupiedField("Free Field", Troop(1, Team.NO_TEAM))),
          "NW" -> Some(OccupiedField("Free Field", Troop(1, Team.NO_TEAM)))
        ))
      }
      "mark neighbors out of the bounds as None" in {
        val edgeNeighbors = new Neighbors(0, 0, fields)
        edgeNeighbors.neighborMap should be(Map(
          "N" -> None,
          "NE" -> None,
          "E" -> Some(OccupiedField("Free Field", Troop(1, Team.NO_TEAM))),
          "SE" -> Some(OccupiedField("Free Field", Troop(1, Team.NO_TEAM))),
          "S" -> Some(OccupiedField("Free Field", Troop(1, Team.NO_TEAM))),
          "SW" -> None,
          "W" -> None,
          "NW" -> None
        ))

        val otherEdgeNeighbors = new Neighbors(2, 2, fields)
        otherEdgeNeighbors.neighborMap should be(Map(
          "N" -> Some(OccupiedField("Free Field", Troop(1, Team.NO_TEAM))),
          "NE" -> None,
          "E" -> None,
          "SE" -> None,
          "S" -> None,
          "SW" -> None,
          "W" -> Some(OccupiedField("Free Field", Troop(1, Team.NO_TEAM))),
          "NW" -> Some(OccupiedField("Free Field", Troop(1, Team.NO_TEAM)))
        ))
      }
      "return its center position and the field all neighbors are depending on" in {
        val changedMatrix = fields.replaceField(1, 1, Field("Center OccupiedField", Troop(1, Team.NO_TEAM)))
        val neighbors = new Neighbors(1, 1, changedMatrix)

        neighbors.center() should be(OccupiedField("Center OccupiedField", Troop(1, Team.NO_TEAM)))
      }
      "state if its a valid neighbor neighborMap or not. It is only valid if the neighborMap contains at least on type of OccupiedField" in {
        val allBlockedFields = fields.fill(Field("x"))
        val neighbors = new Neighbors(1, 1, allBlockedFields)

        neighbors.valid() should be(false)

        val atleastOneField = allBlockedFields.replaceField(0, 1, Field("free"))
        val validNeighbors = new Neighbors(1, 1, atleastOneField)

        validNeighbors.valid() should be(true)
      }
    }
  }
}
