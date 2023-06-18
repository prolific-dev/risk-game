package de.htwg.se.riskgame.model.fileIoComponent.fileIoJsonImpl

import akka.actor.typed.ActorSystem
import akka.stream.ActorMaterializer
import akka.actor.typed.scaladsl.Behaviors
import scala.concurrent.Future
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest
import akka.http.scaladsl.model._
import akka.util.ByteString
import akka.http.scaladsl.model.headers._
import scala.util.{Failure, Success, Try}
import play.api.libs.json.JsValue
import play.api.libs.json.Json
import scala.concurrent.ExecutionContext
import scala.concurrent.Await
import scala.concurrent.duration._

object FileIoAPI:

    def sendSaveRequest(jsonString: String): Unit =
        implicit val system = ActorSystem(Behaviors.empty, "SaveRequest")
        implicit val executionContext = system.executionContext

        val responseFuture: Future[HttpResponse] = 
          Http().singleRequest(
            HttpRequest(
              method = HttpMethods.POST,
              uri = "http://localhost:8081/save",
              entity = HttpEntity(ContentTypes.`application/json`, ByteString(jsonString))))

        responseFuture
          .onComplete {
            case Success(res) => println(res)
            case Failure(_)   => sys.error("Something went wrong") 
          }

    def sendLoadRequest(id: Int): Option[String] =
        implicit val system = ActorSystem(Behaviors.empty, "LoadRequest")
        implicit val executionContext = system.executionContext

        val responseFuture: Future[HttpResponse] = 
          Http().singleRequest(
            HttpRequest(
              method = HttpMethods.GET,
              uri = "http://localhost:8081/load/0",
            )
          )

        val maybeStringFuture: Future[Option[String]] = responseFuture.flatMap { response => 
          response.status match
            case StatusCodes.OK =>
                response.entity.dataBytes.runFold(ByteString.empty)(_ ++ _).map { body =>
                    body.utf8String
                }.map(Some.apply)
            case _ =>
              response.discardEntityBytes()
              Future.successful(None)
        }

        var maybeString: Option[String] = Await.result(maybeStringFuture, 5.seconds)

        maybeString


