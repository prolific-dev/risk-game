package de.htwg.se.riskgame.util

trait Command {
  def doStep: Unit

  def undoStep: Unit

  def redoStep: Unit

}
