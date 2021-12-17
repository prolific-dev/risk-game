package de.htwg.se.riskgame.model.deskComponent.deskBasicImpl

case class Matrix[Field](rows: Vector[Vector[Field]]) {
  val size: Int = rows.size

  def this(size: Int, filling: Field) = this(Vector.tabulate(size, size) { (row, col) => filling })

  def field(row: Int, col: Int): Field = rows(row)(col)

  def fill(filling: Field): Matrix[Field] = copy(Vector.tabulate(size, size) { (row, col) => filling })

  def replaceField(row: Int, col: Int, field: Field): Matrix[Field] = copy(rows.updated(row, rows(row).updated(col, field)))
}
