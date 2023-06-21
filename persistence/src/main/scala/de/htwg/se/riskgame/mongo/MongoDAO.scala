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
  val database_pw = sys.env.getOrElse("MONGO_INITDB_ROOT_PASSWORD", "mongo")
  val database_username = sys.env.getOrElse("MONGO_INITDB_ROOT_USERNAME", "root")
  val host = sys.env.getOrElse("MONGO_INITDB_HOST", "riskgame-mongo")
  val port = sys.env.getOrElse("MONGO_INITDB_PORT", "27017")

  val uri: String = s"mongodb://$database_username:$database_pw@$host:$port/?authSource=admin"
  private val client: MongoClient = MongoClient(uri)
  val db: MongoDatabase = client.getDatabase("riskgame")
  var gameCollection: MongoCollection[Document] = db.getCollection("game")
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
