package de.htwg.se.riskgame

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.Done
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat

import scala.io.StdIn

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

object MapCreatorService:

    implicit val system: ActorSystem[_] = ActorSystem(Behaviors.empty, "SprayExample")
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext: ExecutionContext = system.executionContext

    var orders: List[Item] = Nil

    //domain model
    final case class Item(name: String, id: Long)
    final case class Order(items: List[Item])

    // formats for unmarshalling and marshalling
    implicit val itemFormat: RootJsonFormat[Item] = jsonFormat2(Item.apply)
    implicit val orderFormat: RootJsonFormat[Order] = jsonFormat1(Order.apply)

    // (fake) async database query api
    def fetchItem(itemId: Long): Future[Option[Item]] = Future {
        orders.find(o => o.id == itemId)
    }
    def saveOrder(order: Order): Future[Done] = {
        orders = order.items ::: orders
        Future { Done }
    }

    def mapCreatorService(): Unit =
        val route: Route =
            concat(
                get {
                    pathPrefix("item" / LongNumber) { id =>
                        // there might be no item for a given id
                        val maybeItem: Future[Option[Item]] = fetchItem(id)

                        onSuccess(maybeItem) {
                            case Some(item) => complete(item)
                            case None       => complete(StatusCodes.NotFound)
                        }
                    }
                },
                post {
                    path("create-order") {
                        entity(as[Order]) { order =>
                            val saved: Future[Done] = saveOrder(order)
                            onSuccess(saved) { _ => // we are not interested in the result value 'Done' but only in the fact that it was successful
                                complete("order created")
                            }
                        }
                    }
                }
            )

        val bindingFuture = Http().newServerAt("localhost", 8082).bind(route)
        println(s"Server now online. Please navigate to http://localhost:8082/hello\nPress RETURN to stop...")
        StdIn.readLine() // let it run until user presses return
        bindingFuture
            .flatMap(_.unbind()) // trigger unbinding from the port
            .onComplete(_ => system.terminate()) // and shutdown when done
