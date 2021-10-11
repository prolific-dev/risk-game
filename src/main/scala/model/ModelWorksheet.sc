import de.htwg.se.riskgame.model.{Desk, Field, Matrix}

val desk = new Desk(new Matrix[Field](2, Field("x")))

print(desk.toString)