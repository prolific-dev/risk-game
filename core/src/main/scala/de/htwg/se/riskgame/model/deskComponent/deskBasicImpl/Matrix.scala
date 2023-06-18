package de.htwg.se.riskgame.model.deskComponent.deskBasicImpl

import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.Field

case class Matrix[+F <: Field](rows: Vector[Vector[F]]):

  val size: Int = rows.size
  
  def this(size: Int) = this(Vector.tabulate(size, size) { (row, col) => Field.apply("blocked", row * size + col + 1, row, col, size, "", 0).asInstanceOf[F] })
  
  def fieldById(id: Int): Option[F] = rows.flatten.find(_.id == id)

  def fieldByCoords(row: Int, col: Int): F = rows(row)(col)

  def replaceField(row: Int, col: Int, field: Field): Matrix[F] = copy(rows.updated(row, rows(row).updated(col, field.asInstanceOf[F])))
