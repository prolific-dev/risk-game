package de.htwg.se.riskgame.model.mapCreatorComponent.mapCreatorDefault

import de.htwg.se.riskgame.model.mapCreatorComponent.MapCreatorTemplate
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.Field
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.Matrix

class MapCreator extends MapCreatorTemplate:

  override def createMap: Matrix[Field] = 
    var matrix = new Matrix[Field](3)
    
    for (row <- 0 until matrix.size; col <- 0 until matrix.size)
        if (row == 0 || row == matrix.size - 1 || col == 0 || col == matrix.size - 1)
            val field: Field = Field.apply(matrix.fieldByCoords(row, col).id, row, col, matrix.size, 0, None, "", 0) 
            matrix = matrix.replaceField(row, col, field)

    matrix