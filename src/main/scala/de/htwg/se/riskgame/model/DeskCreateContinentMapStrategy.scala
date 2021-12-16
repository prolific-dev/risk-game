package de.htwg.se.riskgame.model

class DeskCreateContinentMapStrategy extends DeskCreateStrategyTemplate {
  val fixSize: Int = 4

  def createDesk(): Desk =
    var desk = prepare(fixSize)
    desk = fill(desk)
    postProcess(desk)

  override def prepare(size: Int): Desk = Desk(new Matrix[Field](fixSize, Field("x")), new DeskInfo())

  override def fill(desk: Desk): Desk = {
    var _desk = desk

    val continents = Vector[(Int, Int, Field)](
      (1, 0, Field("North America", Troop(1, Team.NO_TEAM))),
      (2, 0, Field("South America", Troop(1, Team.NO_TEAM))),
      (1, 1, Field("Europe", Troop(1, Team.NO_TEAM))),
      (2, 2, Field("Africa", Troop(1, Team.NO_TEAM))),
      (1, 3, Field("Asia", Troop(1, Team.NO_TEAM))),
      (2, 3, Field("Australia", Troop(1, Team.NO_TEAM)))
    )

    continents.foreach(continent => _desk = _desk.set(continent._1, continent._2, continent._3))
    _desk

  }
}
