package de.htwg.se.riskgame.controller

enum GameStatus (status: String):
  def message: String = status

  case IDLE extends GameStatus ("")
  case EMPTY extends GameStatus ("A new empty game was created")
  case NEW extends GameStatus ("A new game was created")
  case SET extends GameStatus ("A field was set")
  case SAVED extends GameStatus ("Game was saved")
  case LOADED extends GameStatus ("Game was loaded")
  case UNDO extends GameStatus ("Undone one step")
  case REDO extends GameStatus ("Redone one step")
  case WON extends GameStatus ("Player won the game")
  case LOST extends GameStatus ("Player lost the game")
  case CLOSING extends GameStatus ("Game is closing")
  end GameStatus

