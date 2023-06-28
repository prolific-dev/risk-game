package de.htwg.se.riskgame.fileio

import akka.Done
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport.*
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.*
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.unmarshalling.FromRequestUnmarshaller
// import de.htwg.se.riskgame.slick.SlickDAO
import play.api.libs.json.{JsObject, JsValue, Json}
import spray.json.DefaultJsonProtocol.*
import spray.json.*

import java.io.{File, PrintWriter}
import java.nio.file.{FileSystem, Files}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future}
import scala.io.{Source, StdIn}
import scala.util.{Failure, Success, Try}

class FileIOService:
  implicit val system: ActorSystem[_] = ActorSystem(Behaviors.empty, "FileIOService")
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext: ExecutionContext = system.executionContext

  case class MyJsonData(data: JsObject)

  def listMaps: Future[Option[String]] = Future {
    val lastFile = new File("fileIO/src/main/resources/memory")
    val fileList = lastFile.listFiles(_.isFile()).map(_.getPath()).toList

    fileList.size match
      case 0 => None
      case _ =>
        val sb: StringBuilder = new StringBuilder
        fileList.foreach(s => sb.append(s"${s},\n"))
        Some(sb.toString().dropRight(2))
  }

  def loadMap(id: Int): Future[Option[String]] = Future {
    val lastFile = new File("fileIO/src/main/resources/memory")
    val fileList = lastFile.listFiles(_.isFile()).map(_.getPath()).toList

    val path = fileList.head
    val source = Source.fromFile(path)
    val content = source.mkString
    source.close()
    Some(content)
  }

  def saveMap(jsonString: String): Future[Done] = {
    Try(new File(s"src/main/resources/memory/${nextId}_SaveGame.json")) match
      case Failure(exception) => println(exception)
      case Success(file) =>
        val pw = new PrintWriter(file)
        val json = Json.parse(jsonString).as[JsObject]
        pw.write(Json.prettyPrint(json))
        pw.close()
    Future {
      Done
    }
  }

  def nextId: Int = {
    val lastFile = new File("src/main/resources/memory")
    val fileList = lastFile.listFiles(_.isFile()).map(_.getPath()).toList
    fileList.size
  }
  def fileioservice(): Unit =
    val route: Route =
      concat(
        get {
          pathPrefix("load" / IntNumber) { id =>
            val maybeJson: Future[Option[String]] = loadMap(id)
            onComplete(maybeJson) {
              case Success(jsonString) => complete(jsonString)
              case Failure(_) => complete(StatusCodes.BadGateway)
            }
          } ~
            pathPrefix("list") {
              val maybeList: Future[Option[String]] = listMaps
              onSuccess(maybeList) {
                case Some(list) => complete(list)
                case None => complete(StatusCodes.NoContent)
              }
            }
        },
        post {
          path("save") {
            entity(as[String]) { jsonString =>
              val saved: Future[Done] = saveMap(jsonString)
              onSuccess(saved) { _ => 
                complete("map saved")
              }
            }
          }
        }
      )
    val map = loadMap(0)
    map.onComplete(o =>
      o.get match
        case None => println("None")
        case Some(js) => println(js.toString())
    )

    val bindingFuture = Http().newServerAt("localhost", 8082).bind(route)
    println(s"Server now online. Press RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done

