package de.htwg.se.riskgame.model.fileIoComponent.fileIoJsonImpl


import com.google.inject.{Guice, Inject}
import de.htwg.se.riskgame.RiskGameModule
import de.htwg.se.riskgame.model.deskComponent.DeskInterface
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.Field
import de.htwg.se.riskgame.model.fileIoComponent.FileIOInterface
import de.htwg.se.riskgame.model.teamComponent.Team
import de.htwg.se.riskgame.model.troopComponent.Troop
import play.api.libs.json.JsLookupResult.jsLookupResultToJsLookup
import play.api.libs.json.{JsNumber, JsObject, Json}

import java.io.*
import scala.io.Source

class FileIO @Inject extends FileIOInterface {
  override def load: DeskInterface = {
    val injector = Guice.createInjector(new RiskGameModule)
    var desk = injector.getInstance(classOf[DeskInterface])
    val source = Source.fromFile("desk.json").getLines().mkString
    val json = Json.parse(source)
    val size = (json \ "desk" \ "size").get.toString().toInt

    for (index <- 0 until size * size) {
      val row = (json \\ "row") (index).as[Int]
      val col = (json \\ "col") (index).as[Int]
      val name = (json \\ "name") (index).as[String]

      name match {
        case "x" => desk.set(row, col, Field("x"))
        case _ =>
          val troops = (json \\ "troops") (index).as[Int]
          val team = (json \\ "team") (index).as[String] match {
            case "No Team" => Team.NO_TEAM
            case "Blue" => Team.BLUE
            case "Red" => Team.RED
          }
          desk = desk.set(row, col, Field(name, Troop(troops, team)))
      }
    }
    desk
  }

  override def save(desk: DeskInterface): Unit =
    val pw = new PrintWriter(new File("desk.json"))
    pw.write(Json.prettyPrint(deskToJson(desk)))
    pw.close()

  def deskToJson(desk: DeskInterface): JsObject = {
    Json.obj(
      "desk" -> Json.obj(
        "size" -> desk.size,
        "teams" -> desk.info.teams.toString(),
        "playerTurn" -> desk.info.playerTurn,
        "fields" -> Json.toJson(
          for {
            row <- 0 until desk.size
            col <- 0 until desk.size
          } yield fieldToJson(desk, row, col)
        )
      )
    )
  }

  def fieldToJson(desk: DeskInterface, row: Int, col: Int): JsObject = {
    Json.obj(
      "row" -> row,
      "col" -> col,
      "name" -> desk.field(row, col).getName,
      "troops" -> JsNumber(desk.field(row, col).getTroop match {
        case None => -1
        case Some(troop) => troop.amount
      }),
      "team" -> desk.field(row, col).team.getName
    )
  }
}
