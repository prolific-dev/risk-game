package de.htwg.se.riskgame.model.deskComponent.deskBasicImpl

import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.DeskInfo
import de.htwg.se.riskgame.model.teamComponent.Team
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class DeskInfoSpec extends AnyWordSpec with Matchers {
  "A DeskInfo" when {
    "new" should {
      val info = DeskInfo(IndexedSeq[Team](Team.BLUE, Team.RED), 1, None)
      "contain the input team sequence" in {
        info.teams should be(IndexedSeq[Team](Team.BLUE, Team.RED))
      }
      "contain the current playerTurn" in {
        info.playerTurn should be(1)
      }
      "return the current team which has the turn" in {
        info.currentPlayerTurn should be(Team.RED)
      }
      "increment the playerTurn value" in {
        var updatedInfo = info.endTurn
        updatedInfo.playerTurn should be(0)
        updatedInfo = updatedInfo.endTurn
        updatedInfo.playerTurn should be(1)
        updatedInfo = updatedInfo.endTurn
        updatedInfo.playerTurn should be(0)
      }
    }
    "default" should {
      val info = new DeskInfo()
      "have a default team sequence" in {
        info.teams should be(IndexedSeq[Team](Team.BLUE, Team.RED))
      }
      "have a default playerTurn count on 0" in {
        info.playerTurn should be(0)
      }
    }
  }

}
