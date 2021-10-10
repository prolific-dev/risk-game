package de.htwg.se.riskgame

import model.Player

object RiskGame {
  def main(args: Array[String]): Unit = {
    val player = Player("Your Name")
    println("Hello, " + player.name + ". Welcome to Risk Game!")
  }

}
