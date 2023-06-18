package de.htwg.se.riskgame.clone.model.deskComponent.deskBasicImpl

class Desk(fields: Matrix[Field]) {

  def size: Int = fields.size

  override def toString: String =

    val borderLeft = "| "
    val borderRight = " |\n"
    val borderTopBottom = borderLeft + (" - " * size) + borderRight

    var totalField = ""

    def _rowString(row: Int): String =
      var totalRow = borderLeft
      for (col <- 0 until size) totalRow = totalRow.concat(" " + fields.field(row, col).toString + " ")
      totalRow.concat(borderRight)

    for (row <- 0 until size) totalField = totalField.concat(_rowString(row))

    borderTopBottom.concat(totalField).concat(borderTopBottom)

}
