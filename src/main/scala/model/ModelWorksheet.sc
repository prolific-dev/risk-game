import de.htwg.se.riskgame.model.{Desk, LegalField, Matrix}

val desk = new Desk(new Matrix[LegalField](2, LegalField("x")))

print(desk.toString)