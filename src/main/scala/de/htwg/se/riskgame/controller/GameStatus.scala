package de.htwg.se.riskgame.controller

enum GameStatus(status: String):

  case IDLE extends GameStatus("")
  case WON extends GameStatus("Player won the game")
  case LOST extends GameStatus("Player lost the game")

end GameStatus

