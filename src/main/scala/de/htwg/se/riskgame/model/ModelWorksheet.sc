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

val desk = new Desk(3)

val last = desk.size - 1


for {
  i <- 0 until desk.size
  j <- 0 until desk.size
} yield (
  (i, j) match {
    case (_, 0) => "| " + desk.field(i, j).toString + "  "
    case (_, last) => "  " + desk.field(i, j).toString + "  |\n"
    case _ => "  " + desk.field(i, j).toString + "  "
  }).foreach(print(_))
