import de.htwg.se.riskgame.model.*

val desk = new Desk(3)

val map: Map[String, Option[(Int, Int)]] = desk.neighbors(1, 1).neighborMap map {case(k, v) => if (v.isDefined) (k -> (k match {
  case "N" => Some((1 - 1, 1))
  case "NE"=> Some((1 - 1, 1 + 1))
  case "E" => Some((1, 1 + 1))
  case "SE" => Some((1 + 1, 1 + 1))
  case "S" => Some((1 + 1, 1))
  case "SW" => Some((1 + 1, 1 - 1))
  case "W" => Some((1, 1 - 1))
  case "NW" => Some((1 - 1, 1 - 1))
})) else (k -> None)}

map("N")

//val last = desk.size - 1
//
//
//for {
//  i <- 0 until desk.size
//  j <- 0 until desk.size
//} yield (
//  (i, j) match {
//    case (_, 0) => "| " + desk.field(i, j).toString + "  "
//    case (_, last) => "  " + desk.field(i, j).toString + "  |\n"
//    case _ => "  " + desk.field(i, j).toString + "  "
//  }).foreach(print(_))

val g: Option[Int] = Some(3)

g.isEmpty

