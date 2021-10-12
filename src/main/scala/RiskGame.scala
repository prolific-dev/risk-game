package de.htwg.se.riskgame

import model.{Desk, LegalField, Player}

object RiskGame {
  def main(args: Array[String]): Unit = {
    val player = new Player("Your Name")
    println("Hello, " + player.name + ". Welcome to Risk Game!")
    println()
    print("Your Game Desk:")
    println(new Desk(3))
  }

}
