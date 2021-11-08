package de.htwg.se.riskgame
package model

case class Matrix[IField](rows: Vector[Vector[IField]]) {
  val size: Int = rows.size

  def this(size: Int, filling: IField) = this(Vector.tabulate(size, size) { (row, col) => filling })

  def field(row: Int, col: Int): IField = rows(row)(col)

  def fill(filling: IField): Matrix[IField] = copy(Vector.tabulate(size, size) { (row, col) => filling })

  def replaceField(row: Int, col: Int, field: IField): Matrix[IField] = copy(rows.updated(row, rows(row).updated(col, field)))
}
