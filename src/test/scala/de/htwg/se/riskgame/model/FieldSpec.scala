package de.htwg.se.riskgame.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.io.AnsiColor
import scala.io.AnsiColor.*

class FieldSpec extends AnyWordSpec with Matchers {
  "A field" when {
    "called as an object with the factory method pattern" should {
      val blockedField = Field("x")
      val occupiedField = Field("free")
      val definedOccupiedField = Field("Defined Field", new Troop(3))
      "be instance of" in {
        blockedField.isInstanceOf[BlockedField] should be(true)
        occupiedField.isInstanceOf[OccupiedField] should be(true)
        definedOccupiedField.isInstanceOf[OccupiedField] should be(true)
      }
    }
    "call directly by apply method" should {
      val blockedField = Field.apply("x")
      val occupiedField = Field.apply("free")
      val definedOccupiedField = Field.apply("Defined Field", new Troop(3))
      "be instance of" in {
        blockedField.isInstanceOf[BlockedField] should be(true)
        occupiedField.isInstanceOf[OccupiedField] should be(true)
        definedOccupiedField.isInstanceOf[OccupiedField] should be(true)
      }
    }
    "as OccupiedField" should {
      val field = new OccupiedField("OccupiedField", new Troop(1))
      "have default Troop set" in {
        field.getTroop should be(Some(new Troop(1)))
      }
      "have no team" in {
        field.team should be(Team.NO_TEAM)
      }
      "should not be set" in {
        field.isSet should be(false)
      }
      "have a nice String representation" in {
        field.toString should be("1")
      }
      "have a colorized String representation of the associated team" in {
        val colorizedField = new OccupiedField("Colorized Field", Troop(3, Team.BLUE))
        colorizedField.toString should be(colorizedField.team.getAnsi + colorizedField.troop.amount + RESET)
      }
      "have a highlighted String representation" in {
        val highlightedField = OccupiedField("Highlighted Field", Troop(3, Team.BLUE), true)
        highlightedField.toString should be(YELLOW + highlightedField.troop.amount + RESET)
      }
    }
    "as BlockedField" should {
      val field = BlockedField()
      "have no highlight" in {
        field.highlightOn should be(field)
        field.highlightOff should be(field)
      }
      "have no troops" in {
        field.getTroop should be(None)
      }
      "have no team" in {
        field.team should be(Team.NO_TEAM)
      }
      "should not be set" in {
        field.isSet should be(true)
      }
      "have a nice String representation" in {
        field.toString should be("x")
      }
    }
  }

}
