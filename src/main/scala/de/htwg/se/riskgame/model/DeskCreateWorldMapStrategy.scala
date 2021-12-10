package de.htwg.se.riskgame.model

class DeskCreateWorldMapStrategy extends DeskCreateStrategyTemplate {
  val fixSize: Int = 10

  def createDesk(): Desk =
    var desk = prepare(fixSize)
    desk = fill(desk)
    postProcess(desk)

  override def prepare(size: Int): Desk = Desk(new Matrix[Field](10, Field("x")), new DeskInfo())

  override def fill(desk: Desk): Desk = {
    var _desk = desk

    val northAmerica = Vector[(Int, Int, Field)](
      (1, 3, Field("Greenland", Troop(1, Team.NO_TEAM))),
      (2, 0, Field("Alaska", Troop(1, Team.NO_TEAM))),
      (2, 1, Field("West Canada", Troop(1, Team.NO_TEAM))),
      (2, 2, Field("East Canada", Troop(1, Team.NO_TEAM))),
      (3, 0, Field("West America", Troop(1, Team.NO_TEAM))),
      (3, 1, Field("East America", Troop(1, Team.NO_TEAM))),
      (4, 0, Field("Mexico", Troop(1, Team.NO_TEAM)))
    )
    val southAmerica = Vector[(Int, Int, Field)](
      (5, 1, Field("Colombia", Troop(1, Team.NO_TEAM))),
      (5, 2, Field("Venezuela", Troop(1, Team.NO_TEAM))),
      (5, 3, Field("Brazil", Troop(1, Team.NO_TEAM))),
      (6, 2, Field("Bolivia", Troop(1, Team.NO_TEAM))),
      (7, 2, Field("Argentina", Troop(1, Team.NO_TEAM)))
    )
    val europe = Vector[(Int, Int, Field)](
      (2, 4, Field("Great Britain", Troop(1, Team.NO_TEAM))),
      (2, 5, Field("North Europe", Troop(1, Team.NO_TEAM))),
      (3, 4, Field("Iberian Island", Troop(1, Team.NO_TEAM))),
      (3, 5, Field("South Europe", Troop(1, Team.NO_TEAM)))
    )
    val africa = Vector[(Int, Int, Field)](
      (4, 4, Field("West Africa", Troop(1, Team.NO_TEAM))),
      (4, 5, Field("East Africa", Troop(1, Team.NO_TEAM))),
      (5, 5, Field("Central Africa", Troop(1, Team.NO_TEAM))),
      (6, 5, Field("South Africa", Troop(1, Team.NO_TEAM)))
    )
    val asia = Vector[(Int, Int, Field)](
      (2, 6, Field("West Russia", Troop(1, Team.NO_TEAM))),
      (2, 7, Field("Central Russia", Troop(1, Team.NO_TEAM))),
      (2, 8, Field("East Russia", Troop(1, Team.NO_TEAM))),
      (3, 6, Field("West Asia", Troop(1, Team.NO_TEAM))),
      (3, 7, Field("China", Troop(1, Team.NO_TEAM))),
      (4, 6, Field("Arabia", Troop(1, Team.NO_TEAM))),
      (4, 8, Field("South East Asia", Troop(1, Team.NO_TEAM))),
      (5, 8, Field("Indonesia", Troop(1, Team.NO_TEAM))),
      (5, 9, Field("Philippines", Troop(1, Team.NO_TEAM)))
    )
    val australia = Vector[(Int, Int, Field)](
      (6, 9, Field("East Australia", Troop(1, Team.NO_TEAM))),
      (7, 8, Field("West Australia", Troop(1, Team.NO_TEAM)))
    )
    val allContinents = Vector[Vector[(Int, Int, Field)]](northAmerica, southAmerica, europe, africa, asia, australia)
    allContinents.foreach(continent => continent.foreach(f => _desk = _desk.set(f._1, f._2, f._3)))
    _desk
  }

  override def postProcess(desk: Desk): Desk = if (desk.valid) desk else new Desk(3)

}
