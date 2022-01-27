package de.htwg.se.riskgame.model.fileIoComponent

import de.htwg.se.riskgame.model.deskComponent.DeskInterface

trait FileIOInterface {
  def load: DeskInterface

  def save(desk: DeskInterface): Unit

  def loadMap(desk: DeskInterface): DeskInterface

  def loadGuiMapDataPath(): Map[String, String]

}
