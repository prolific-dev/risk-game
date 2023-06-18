import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.Done
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.unmarshalling.FromRequestUnmarshaller
import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat
import spray.json._
import play.api.libs.json.{JsObject, Json}
import play.api.libs.json.JsValue
import scala.io.StdIn
import scala.io.Source
import scala.util.{Failure, Success, Try}
import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import java.nio.file.{FileSystem, Files}
import java.io.File
import java.io.PrintWriter

import scala.concurrent.ExecutionContext.Implicits.global
import de.htwg.se.riskgame.fileio.SlickDAO

object FileIOService {

    def main(args: Array[String]) = myapi()

    implicit val system: ActorSystem[_] = ActorSystem(Behaviors.empty, "FileIOService")
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext: ExecutionContext = system.executionContext
    
    case class MyJsonData(data: JsObject)

    def loadMap(id: Int): Future[Option[String]] = Future {
        val lastFile = new File("fileIO/src/main/resources/memory")
        val fileList = lastFile.listFiles(_.isFile()).map(_.getPath()).toList

        val path = fileList.head
        val source = Source.fromFile(path)
        val content = source.mkString
        source.close()
        Some(content)
    }

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

    def saveMap(jsonString: String): Future[Done] = {
        Try(new File(s"src/main/resources/memory/${nextId}_SaveGame.json")) match
          case Failure(exception) => println(exception)
          case Success(file)      =>
            val pw   = new PrintWriter(file)
            val json = Json.parse(jsonString).as[JsObject]
            pw.write(Json.prettyPrint(json))
            pw.close()
        Future { Done }
    }

    def nextId: Int = {
        val lastFile = new File("src/main/resources/memory")
        val fileList = lastFile.listFiles(_.isFile()).map(_.getPath()).toList
        fileList.size
    }
    
    def myapi(): Unit =

        val route: Route =
            concat(
                get {
                    pathPrefix("load" / IntNumber) { id =>
                        val maybeJson: Future[Option[String]] = loadMap(id)

                        onComplete(maybeJson) {
                            case Success(jsonString) => complete(jsonString)
                            case Failure(_)          => complete(StatusCodes.BadGateway)
                        }
                    } ~
                    pathPrefix("list") {
                        val maybeList: Future[Option[String]] = listMaps
                        onSuccess(maybeList) {
                            case Some(list) => complete(list)
                            case None       => complete(StatusCodes.NoContent)
                        }
                    }
                },
                post {
                    path("save") {
                        entity(as[String]) { jsonString =>
                            val saved: Future[Done] = saveMap(jsonString)
                            onSuccess(saved) { _ => // we are not interested in the result value 'Done' but only in the fact that it was successful
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


        val bindingFuture = Http().newServerAt("localhost", 8081).bind(route)
        val slickDAO = new SlickDAO
        println(s"Server now online. Press RETURN to stop...")
        StdIn.readLine() // let it run until user presses return
        bindingFuture
            .flatMap(_.unbind()) // trigger unbinding from the port
            .onComplete(_ => system.terminate()) // and shutdown when done


}

