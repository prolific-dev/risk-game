package de.htwg.se.riskgame.model.fileIoComponent.fileIoJsonImpl

import de.htwg.se.riskgame.model.deskComponent.DeskInterface
import de.htwg.se.riskgame.model.deskComponent.deskBasicImpl.{AreaField, BlockedField, BridgeField, Desk, Field, Info, Matrix, Player, Team}
import de.htwg.se.riskgame.model.fileIoComponent.FileIoInterface
import play.api.libs.json._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat
import java.io.{File, PrintWriter}
import scala.io.Source
import scala.util.{Failure, Success, Try}


class FileIo extends FileIoInterface:
  override def load: DeskInterface =
    
    FileIoAPI.sendLoadRequest(0) match
      case None       => new Desk(3)
      case Some(jsonString) =>
        val json   = Json.parse(jsonString)
        val size   = (json \ "desk" \ "size").get.toString().toInt
        var fields = new Matrix(size)

        for (index <- 0 until size * size)
          val fieldType = (json \\ "type")     (index).as[String]
          val name      = (json \\ "name")     (index).as[String]
          val x         = (json \\ "x")        (index).as[Int]
          val y         = (json \\ "y")        (index).as[Int]
          val id        = (json \\ "id")       (index).as[Int]
          val areaId    = (json \\ "areaId")   (index).as[Int]
          val soldiers  = (json \\ "soldiers") (index).as[Int]
          val occupier  =
            (json \\ "occupier") (index).as[Int] match
              case -1 => None
              case id => Some(Team.fromOrdinal(id))

          fieldType match
            case "area"    => fields = fields.replaceField(x, y, Field(id, x, y, size, soldiers, occupier, name, areaId))
            case "blocked" => fields = fields.replaceField(x, y, Field("blocked", id, x, y, size, name, areaId))
            case "bridge"  => fields = fields.replaceField(x, y, Field("bridge", id, x, y, size, name, areaId))

        val desk                   = new Desk(fields)
        val selectedFieldPrimary   = desk.fieldById((json \ "info" \ "selectedFieldPrimary").get.toString().toInt)
        val selectedFieldSecondary = desk.fieldById((json \ "info" \ "selectedFieldSecondary").get.toString().toInt)
        val playerTurn             = (json \ "info" \ "playerTurn").as[Int]
        val playerList             =
          (json \ "info" \ "playerList").as[JsObject]
            .values
            .flatMap {
              case JsNumber(value) => Some(new Player(value.toInt))
              case _               => None
            }.toList
          
        Desk(fields, Info(selectedFieldPrimary, selectedFieldSecondary, playerList, playerTurn))



  override def save(desk: DeskInterface): Unit =
    val jsonString = deskToJson(desk).toString()
    FileIoAPI.sendSaveRequest(jsonString)


  private def deskToJson(desk: DeskInterface): JsObject =
    Json.obj(
      "info" -> infoToJson(desk.info),
      "desk" -> Json.obj(
        "size"       -> desk.size,
        "fields"     -> Json.toJson(
          for {
            row <- 0 until desk.size
            col <- 0 until desk.size
          } yield fieldToJson(desk.fieldByCoords(row, col))
        )
      )
    )

  private def infoToJson(info: Info): JsObject =
    Json.obj(
      "playerTurn" -> info.playerTurn,
      "playerList" -> Json.obj(info.playerList.map(p => p.name -> p.id): _*),
      "selectedFieldPrimary" -> JsNumber(info.selectedFieldPrimary match
        case Some(field: Field) => field.id
        case None => -1),
      "selectedFieldSecondary" -> JsNumber(info.selectedFieldSecondary match
        case Some(field: Field) => field.id
        case None => -1)
    )
  private def fieldToJson(field: Field): JsObject =
    Json.obj(
      "type"        -> JsString(field match
        case _: AreaField    => "area"
        case _: BlockedField => "blocked"
        case _: BridgeField  => "bridge"),
      "x"           -> field.x,
      "y"           -> field.y,
      "id"          -> field.id,
      "name"        -> field.name,
      "areaId"      -> field.areaId,
//      "mapSize"     -> field.mapSize,
      "soldiers" -> JsNumber(
        field match
          case areaField: AreaField => areaField.soldiers
          case _                    => -1
      ),
      "occupier" -> JsNumber(
        field match
          case areaField: AreaField =>
            areaField.occupier match
              case Some(occupier) => occupier.getId
              case None           => -1
          case _                    => -1
      ),
//      "neighbor" -> Json.obj(
//        "top"    -> Json.toJson(field.neighborCoordsTop.getOrElse   ((-1, -1))),
//        "bottom" -> Json.toJson(field.neighborCoordsBottom.getOrElse((-1, -1))),
//        "left"   -> Json.toJson(field.neighborCoordsLeft.getOrElse  ((-1, -1))),
//        "right"  -> Json.toJson(field.neighborCoordsRight.getOrElse ((-1, -1)))
//      )
    )
