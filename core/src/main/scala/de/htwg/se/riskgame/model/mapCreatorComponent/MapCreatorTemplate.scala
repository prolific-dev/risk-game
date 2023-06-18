package de.htwg.se.riskgame.model.mapCreatorComponent

import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.Matrix
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.Field

trait MapCreatorTemplate:

  def createMap: Matrix[Field]

