package de.htwg.se.riskgame.mongo

import org.mongodb.scala.model.*
import org.mongodb.scala.model.Aggregates.*
import org.mongodb.scala.model.Filters.*
import org.mongodb.scala.model.Sorts.*
import org.mongodb.scala.result.{DeleteResult, InsertOneResult, UpdateResult}
import org.mongodb.scala.{Document, MongoClient, MongoCollection, MongoDatabase, Observable, Observer, SingleObservable, result}
import org.mongodb.scala.documentToUntypedDocument
import com.mongodb.ConnectionString
import org.mongodb.scala.SingleObservableFuture
import de.htwg.se.riskgame.DAOInterface

import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success, Try}
import concurrent.duration.DurationInt

class MongoDAO extends DAOInterface:
  val host        = "127.0.0.1"
  val port        = "27017"
  val databaseUrl = s"mongodb://$host:$port/?authSource=romme"

  val mongoClient:    MongoClient               = MongoClient(databaseUrl)
  val database:       MongoDatabase             = mongoClient.getDatabase("risk-game")
  val gameCollection: MongoCollection[Document] = database.getCollection("software-architecture")

  override def save: Unit = ???
  //override def save(id: Int, json: String): Unit = ???
    //println("Game stored in Mongo Database")
    //val gameDoc = Document
    //(
    //  "id"         -> id,
    //  "jsonString" -> json
    //)
    //val insertObservable = gameCollection.insertOne(gameDoc)
    //Await.result(insertObservable.toFuture(), 5.seconds)

  override def load: Unit = ???

  override def list: Unit = ???
