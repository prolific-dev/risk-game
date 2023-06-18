package de.htwg.se.riskgame.model.deskComponent.deskBasicImpl

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class InfoSpec extends AnyWordSpec with Matchers:
    "A Info" when {
        "created" should {
            var info = new Info()
            "have no primary selected field" in {
                info.selectedFieldPrimary shouldBe None
            }
            "have no secondary selected field" in {
                info.selectedFieldSecondary shouldBe None
            }
            "have a selectField function" in {
                val firstField  = Field("bridge", 1, 0, 0, 3, "", 1)
                val secondField = Field("bridge", 2, 0, 1, 3, "", 1)
                val thirdField  = Field("bridge", 3, 1, 1, 3, "", 1)
                
                info = info.selectField(firstField)
                info = info.selectField(secondField)
                
                info.selectedFieldPrimary   shouldBe Some(firstField)
                info.selectedFieldSecondary shouldBe Some(secondField)
                
                info = info.selectField(thirdField)

                info.selectedFieldPrimary   shouldBe None
                info.selectedFieldSecondary shouldBe None
            }
            "have a clearSelectedFields function" in {
                val firstField  = Field("bridge", 1, 0, 0, 3, "", 1)
                val secondField = Field("bridge", 2, 0, 1, 3, "", 1)
                
                info = info.selectField(firstField)
                info = info.selectField(secondField)

                info = info.clearSelectedFields()

                info.selectedFieldPrimary   shouldBe None
                info.selectedFieldSecondary shouldBe None
            }
            "have a string format" in {
                info.toString() shouldEqual ""
                + "\nPlayer-Turn: 1\n"
                + "\nchosenField(1): --"
                + "\nchosenField(2): --\n"

                val firstField  = Field("bridge", 1, 0, 0, 3, "", 1)
                val secondField = Field("bridge", 2, 0, 1, 3, "", 1)
                
                info = info.selectField(firstField)
                info = info.selectField(secondField)

                info.toString() shouldEqual ""
                + "\nPlayer-Turn: 1\n"
                + "\nchosenField(1):  1 (0)(0)(R)"
                + "\nchosenField(2):  2 (0)(0)(R)\n"
                
            }
        }
    }