package de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.deskCreatorComponent

import de.htwg.se.riskgame.model.deskComponent.DeskInterface
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.Desk

trait DeskCreateStrategyTemplate {

  def createDesk(size: Int): DeskInterface =
    var desk = prepare(size)
    desk = fill(desk)
    postProcess(desk)

  def prepare(size: Int): DeskInterface = new Desk(size)

  def fill(desk: DeskInterface): DeskInterface =
  // by default do nothing
    desk

  def postProcess(desk: DeskInterface): DeskInterface =
  // by default do nothing
    desk
}
