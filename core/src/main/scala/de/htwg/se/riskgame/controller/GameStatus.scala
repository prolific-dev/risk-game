package de.htwg.se.riskgame.controller

enum GameStatus(name: String):
  case IDLE    extends GameStatus("IDLE")
  case NEW     extends GameStatus("NEW")

end GameStatus
