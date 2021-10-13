package de.htwg.se.riskgame.model

trait Field {
  def team(): Team

  override def toString: String
}
