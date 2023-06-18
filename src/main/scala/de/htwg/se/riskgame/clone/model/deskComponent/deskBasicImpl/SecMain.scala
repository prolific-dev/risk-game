package de.htwg.se.riskgame.clone.model.deskComponent.deskBasicImpl


  @main def run: Unit = {
    val m = new Matrix(6, Field("x", 3))
    val d: Desk = Desk(m)

    print(d.toString)

  }

