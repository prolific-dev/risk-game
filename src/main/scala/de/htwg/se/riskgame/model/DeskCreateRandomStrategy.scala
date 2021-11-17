package de.htwg.se.riskgame.model

import scala.util.Random

class DeskCreateRandomStrategy() extends DeskCreateStrategyTemplate {

  override def createDesk(size: Int): Desk = {
    var desk = prepare(size)

    // size*2 default amount of BlockedFields
    for {i <- 0 to size * 2} {
      desk = fill(desk)
    }

    postProcess(desk)
  }

  override def prepare(size: Int): Desk = super.prepare(size)

  override def fill(_desk: Desk): Desk = {
    var desk = _desk
    val row = Random.nextInt(desk.size)
    val col = Random.nextInt(desk.size)

    desk = desk.set(row, col, new BlockedField)

    if (!desk.valid()) {
      desk = fill(_desk)
    }

    desk
  }

  override def postProcess(desk: Desk): Desk = super.postProcess(desk)
}
