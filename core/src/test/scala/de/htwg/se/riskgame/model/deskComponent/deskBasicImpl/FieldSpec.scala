package de.htwg.se.riskgame.model.deskComponent.deskBasicImpl

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FieldSpec extends AnyWordSpec with Matchers:
    "A field" when {
        "created by default" should {
          val field = Field(1, 0, 0, 3, 3, None, "FieldName", 1)

          "be of instance AreaField" in {
            field.isInstanceOf[AreaField] shouldBe true
          }
          "have an id" in {
            field.id shouldBe 1
          }
          "have coordinates" in {
            field.x shouldBe 0
            field.y shouldBe 0
          }
          "carry info about mapSize" in {
            field.mapSize shouldBe 3
          }
          "have a name" in {
            field.name shouldBe "FieldName"
          }
          "have an areadId" in {
            field.areaId shouldBe 1
          }
          "have an amount of soldiers" in {
            field.asInstanceOf[AreaField].soldiers shouldBe 3
          }
          "have an occupier" in {
            field.asInstanceOf[AreaField].occupier shouldBe None
          }
        }
        "created specific" should {
          val areaField    = Field("area", 1, 0, 0, 3, "AreaField", 1)
          val blockedField = Field("blocked", 2, 0, 1, 3, "BlockedField", 1)
          val bridgeField  = Field("bridge", 3, 1, 1, 3, "BridgeField", 1)

          "be of specific instance" in {
            areaField.isInstanceOf   [AreaField]     shouldBe true
            blockedField.isInstanceOf[BlockedField]  shouldBe true
            bridgeField.isInstanceOf [BridgeField]   shouldBe true

            areaField.isInstanceOf   [AbstractField] shouldBe true
            blockedField.isInstanceOf[AbstractField] shouldBe true
            bridgeField.isInstanceOf [AbstractField] shouldBe true

            areaField.isInstanceOf   [Field]         shouldBe true
            blockedField.isInstanceOf[Field]         shouldBe true
            bridgeField.isInstanceOf [Field]         shouldBe true
          }
          "have an id" in {
            areaField.id    shouldBe 1
            blockedField.id shouldBe 2
            bridgeField.id  shouldBe 3
          }
          "have coordinates" in {
            areaField.x     shouldBe 0
            areaField.y     shouldBe 0
            blockedField.x  shouldBe 0
            blockedField.y  shouldBe 1
            bridgeField.x   shouldBe 1
            bridgeField.y   shouldBe 1
          }
          "carry info about mapSize" in {
            areaField.mapSize    shouldBe 3
            blockedField.mapSize shouldBe 3
            bridgeField.mapSize  shouldBe 3
          }
          "have a name" in {
            areaField.name    shouldBe "AreaField"
            blockedField.name shouldBe "BlockedField"
            bridgeField.name  shouldBe "BridgeField"
          }
          "have an areadId" in {
            areaField.areaId    shouldBe 1
            blockedField.areaId shouldBe 1
            bridgeField.areaId  shouldBe 1
          }
          "have neighbor coordinates" in {
            areaField.neighborCoordsTop       shouldBe None
            areaField.neighborCoordsBottom    shouldBe Some((0, 1))
            areaField.neighborCoordsLeft      shouldBe None
            areaField.neighborCoordsRight     shouldBe Some((1, 0))

            blockedField.neighborCoordsTop    shouldBe Some((0, 0))
            blockedField.neighborCoordsBottom shouldBe Some((0, 2))
            blockedField.neighborCoordsLeft   shouldBe None
            blockedField.neighborCoordsRight  shouldBe Some((1, 1))

            bridgeField.neighborCoordsTop     shouldBe Some((1, 0))
            bridgeField.neighborCoordsBottom  shouldBe Some((1, 2))
            bridgeField.neighborCoordsLeft    shouldBe Some((0, 1))
            bridgeField.neighborCoordsRight   shouldBe Some((2, 1))
          }
          "have an amount of soldiers if instance of AreaField" in {
            areaField.asInstanceOf[AreaField].soldiers shouldBe 0

            areaField.asInstanceOf[AreaField].setSoldiers(3).soldiers shouldBe 3
          }
          "have an occupier if instance of AreaField" in {
            areaField.asInstanceOf[AreaField].occupier shouldBe None

            areaField.asInstanceOf[AreaField].setOccupier(Some(Team.Blue)).occupier shouldBe Some(Team.Blue)

          }

        }
    }
