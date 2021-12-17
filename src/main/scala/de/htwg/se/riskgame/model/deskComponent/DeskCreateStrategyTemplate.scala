package de.htwg.se.riskgame.model.deskComponent

import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.Desk

trait DeskCreateStrategyTemplate {

  def createDesk(size: Int): Desk =
    var desk = prepare(size)
    desk = fill(desk)
    postProcess(desk)

  def prepare(size: Int): Desk = new Desk(size)

  def fill(desk: Desk): Desk =
  // by default do nothing
    desk

  def postProcess(desk: Desk): Desk =
  // by default do nothing
    desk
}
