package de.htwg.se.riskgame.model.fileIoComponent.fileIoJsonImpl

import de.htwg.se.riskgame.model.deskComponent.DeskInterface
import de.htwg.se.riskgame.model.fileIoComponent.FileIOInterface

class FileIO extends FileIOInterface {
  override def load: DeskInterface = ???

  override def save(desk: DeskInterface): Unit = ???
}
