package de.htwg.se.riskgame.controller.controllerComponent

trait Command {
  def doStep(): Unit

  def undoStep(): Unit

  def redoStep(): Unit
}
