package de.htwg.se.riskgame.model.fileIoComponent

import de.htwg.se.riskgame.model.deskComponent.DeskInterface

trait FileIoInterface:
  def load: DeskInterface

  def save(desk: DeskInterface): Unit
