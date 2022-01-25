package de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.deskCreatorComponent.deskCreatorBasicImpl

import com.google.inject.Inject
import de.htwg.se.riskgame.model.deskComponent.DeskInterface
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.deskCreatorComponent.DeskCreateStrategyTemplate
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.{BlockedField, Desk}

import scala.util.Random

class DeskCreateRandomStrategy() extends DeskCreateStrategyTemplate {

  override def createDesk(size: Int): DeskInterface =
    var desk = prepare(size)

    // size*2 default amount of BlockedFields
    for {i <- 0 to size * 2}
      desk = fill(desk)

    postProcess(desk)

  override def prepare(size: Int): DeskInterface = super.prepare(size)

  override def fill(_desk: DeskInterface): DeskInterface =
    var desk = _desk
    val row = Random.nextInt(desk.size)
    val col = Random.nextInt(desk.size)

    desk = desk.set(row, col, new BlockedField)
    if (desk.valid) return desk
    fill(_desk)

  override def postProcess(desk: DeskInterface): DeskInterface = super.postProcess(desk)
}
