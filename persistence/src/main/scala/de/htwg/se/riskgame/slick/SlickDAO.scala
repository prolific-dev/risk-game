package de.htwg.se.riskgame.slick

import java.sql.SQLNonTransientException
import slick.jdbc.JdbcBackend.Database
import slick.jdbc.MySQLProfile.api.*
import scala.concurrent.Await
import scala.util.{Failure, Success, Try}
import concurrent.duration.DurationInt

import de.htwg.se.riskgame.slick.table.*

class SlickDAO:
    val databaseDB:   String = "risk-game"
    val databaseUser: String = "dennis"
    val databasePW:   String = "password123"
    val databasePort: String = "3306"
    val databaseHost: String = "127.0.0.1"
    val databaseUrl:  String = s"jdbc:mysql://$databaseHost:$databasePort/$databaseDB?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&autoReconnect=true"
    val database = Database.forURL(
        url      = databaseUrl,
        driver   = "com.mysql.cj.jdbc.Driver",
        user     = databaseUser,
        password = databasePW
    )

    val deskTable   = new TableQuery(new DeskTable(_))
    val fieldsTable = new TableQuery(new FieldsTable(_))
    val fieldTable  = new TableQuery(new FieldTable(_))

    val setupDeskTable  : DBIOAction[Unit, NoStream, Effect.Schema] = DBIO.seq(deskTable.schema.createIfNotExists)
    val setupFieldsTable: DBIOAction[Unit, NoStream, Effect.Schema] = DBIO.seq(fieldsTable.schema.createIfNotExists)
    val setupFieldTable : DBIOAction[Unit, NoStream, Effect.Schema] = DBIO.seq(fieldTable.schema.createIfNotExists)

    // Try(Await.result(database.run(setupDeskTable), 5.seconds)) match
    //     case Failure(exception) =>
    //         println("Waiting for DB connection")
    //         Thread.sleep(5000)
    //         Await.result(database.run(setupDeskTable), 5.seconds)
    //     case Success(value) => println("Desk Tables created")

    // Try(Await.result(database.run(setupFieldsTable), 5.seconds)) match
    //     case Failure(exception) =>
    //         println("Waiting for DB connection")
    //         Thread.sleep(5000)
    //         Await.result(database.run(setupFieldsTable), 5.seconds)
    //     case Success(value) => println("Fields Tables created")

    // Try(Await.result(database.run(setupFieldTable), 5.seconds)) match
    //     case Failure(exception) =>
    //         println("Waiting for DB connection")
    //         Thread.sleep(5000)
    //         Await.result(database.run(setupFieldTable), 5.seconds)
    //     case Success(value) => println("Field Tables created")

    try {
        Await.result(database.run(setupDeskTable), 10.seconds)
    } catch {
        case e: SQLNonTransientException =>
            println("Waiting for DB connection")
            Thread.sleep(10000)
            Await.result(database.run(setupDeskTable), 10.seconds)
    }
    println("desk table created")

    try {
        Await.result(database.run(setupFieldsTable), 10.seconds)
    } catch {
        case e: SQLNonTransientException =>
            println("Waiting for DB connection")
            Thread.sleep(10000)
            Await.result(database.run(setupFieldsTable), 10.seconds)
    }
    println("fields table created")

    try {
        Await.result(database.run(setupFieldTable), 10.seconds)
    } catch {
        case e: SQLNonTransientException =>
            println("Waiting for DB connection")
            Thread.sleep(10000)
            Await.result(database.run(setupFieldTable), 10.seconds)
    }
    println("field table created")