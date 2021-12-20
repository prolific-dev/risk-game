package de.htwg.se.riskgame.aview.gui

import javafx.scene.effect.DropShadow
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import scalafx.scene.paint.Color.Blue


class SVGPathMapSpec extends AnyWordSpec with Matchers {
  //  "A SVGPathMap" when {
  //    "SVGPathContinentMap" should {
  //      val continentMap = new SVGPathContinentMap
  //      "have a specific map" in {
  //        val mapPath = continentMap.mapPath
  //        val keys = mapPath.keySet
  //        mapPath.isInstanceOf[Map[String, String]] should be(true)
  //        keys.contains("x") should be(true)
  //        keys.contains("Free Field") should be(true)
  //        keys.contains("North America") should be(true)
  //        keys.contains("South America") should be(true)
  //        keys.contains("Europe") should be(true)
  //        keys.contains("Africa") should be(true)
  //        keys.contains("Asia") should be(true)
  //        keys.contains("Australia") should be(true)
  //      }
  //    }
  //    "SVGField" should {
  //      val svgField = SVGField("North America", Blue, new SVGPathContinentMap)
  //      "have id" in {
  //        svgField.getId should be("North America")
  //      }
  //      "have fill" in {
  //        svgField.getFill should be(javafx.scene.paint.Color.BLUE)
  //      }
  //      "have scaleX and scaleY" in {
  //        svgField.getScaleX should be(2.4)
  //        svgField.getScaleY should be(2.4)
  //      }
  //      "have effect" in {
  //        svgField.getEffect.isInstanceOf[DropShadow] should be(true)
  //      }
  //      "have layoutX and layoutY" in {
  //        svgField.getLayoutX should be(65)
  //        svgField.getLayoutY should be(60)
  //
  //        var otherLayoutSVGField = svgField.copy("South America")
  //        otherLayoutSVGField.getLayoutX should be(140)
  //        otherLayoutSVGField.getLayoutY should be(280)
  //
  //        otherLayoutSVGField = otherLayoutSVGField.copy("Europe")
  //        otherLayoutSVGField.getLayoutX should be(350)
  //        otherLayoutSVGField.getLayoutY should be(10)
  //
  //        otherLayoutSVGField = otherLayoutSVGField.copy("Africa")
  //        otherLayoutSVGField.getLayoutX should be(340)
  //        otherLayoutSVGField.getLayoutY should be(190)
  //
  //        otherLayoutSVGField = otherLayoutSVGField.copy("Asia")
  //        otherLayoutSVGField.getLayoutX should be(605)
  //        otherLayoutSVGField.getLayoutY should be(63)
  //
  //        otherLayoutSVGField = otherLayoutSVGField.copy("Australia")
  //        otherLayoutSVGField.getLayoutX should be(730)
  //        otherLayoutSVGField.getLayoutY should be(300)
  //
  //        otherLayoutSVGField = otherLayoutSVGField.copy("x")
  //        otherLayoutSVGField.getLayoutX should be(0)
  //        otherLayoutSVGField.getLayoutY should be(0)
  //      }
  //    }
  //  }
}
