package de.htwg.se.riskgame.model

trait DeskCreateStrategyTemplate {

  def createDesk(size: Int): Desk = {
    var desk = prepare(size)
    desk = fill(desk)
    desk = postProcess(desk)
    desk
  }

  def prepare(size: Int): Desk = new Desk(size)

  def fill(desk: Desk): Desk = {
    // by default do nothing
    desk
  }

  def postProcess(desk: Desk): Desk = {
    // by default do nothing
    desk
  }
}
