import de.htwg.se.riskgame.model.*

//val matrix = new Matrix[Field](3, Field("x"))
//val desk = new Desk(matrix, Seq[Team](Team.BLUE, Team.RED))


//val sb = new StringBuilder(("\n+-" + ("-----" * desk.size)) + "-+\n")
//(0 until desk.size)
//  .foreach(i => {
//    sb ++= "| ";
//    (0 until desk.size)
//      .foreach(j =>
//        sb ++= "  " + desk.field(i, j).toString + "  ");
//    sb ++= " |\n"
//  })
//sb ++= ("+-" + ("-----" * desk.size)) + "-+\n"
//sb.toString()

val desk = new DeskCreateRandomStrategy().createDesk(5)

desk.valid()
