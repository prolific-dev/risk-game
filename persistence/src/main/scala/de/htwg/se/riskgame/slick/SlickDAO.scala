// package de.htwg.se.riskgame.slick

// import java.sql.SQLNonTransientException
// import slick.jdbc.JdbcBackend.Database
// import slick.jdbc.MySQLProfile.api.*
// import scala.concurrent.Await
// import scala.util.{Failure, Success, Try}
// import concurrent.duration.DurationInt

// import de.htwg.se.riskgame.slick.table.*

// class SlickDAO:

//     val databaseDB: String = sys.env.getOrElse("MYSQL_DATABASE", "riskgame")
//     val databaseUser: String = sys.env.getOrElse("MYSQL_USER", "admin")
//     val databasePassword: String = sys.env.getOrElse("MYSQL_PASSWORD", "root")
//     val databasePort: String = sys.env.getOrElse("MYSQL_PORT", "3306")
//     val databaseHost: String = sys.env.getOrElse("MYSQL_HOST", "localhost")
//     val databaseUrl = s"jdbc:mysql://$databaseHost:$databasePort/$databaseDB?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&autoReconnect=true"

//     val database = Database.forURL(
//       url = databaseUrl,
//       driver = "com.mysql.cj.jdbc.Driver",
//       user = databaseUser,
//       password = databasePassword
//     )

//     val deskTable = new TableQuery(new DeskTable(_))
//     val fieldsTable = new TableQuery(new FieldsTable(_))
//     val fieldTable = new TableQuery(new FieldTable(_))

//     val setup: DBIOAction[Unit, NoStream, Effect.Schema] = DBIO.seq(
//       deskTable.schema.createIfNotExists,
//       fieldsTable.schema.createIfNotExists,
//       fieldTable.schema.createIfNotExists,
//     )

//     Try(Await.result(database.run(deskTable), 5.seconds)) match
//         case Failure(exception) =>
//             println("Waiting for DB connection")
//             Thread.sleep(5000)
//             Await.result(database.run(deskTable), 5.seconds)
//         case Success(value) => println("Desk Tables created")

//     Try(Await.result(database.run(setupFieldsTable), 5.seconds)) match
//         case Failure(exception) =>
//             println("Waiting for DB connection")
//             Thread.sleep(5000)
//             Await.result(database.run(setupFieldsTable), 5.seconds)
//         case Success(value) => println("Fields Tables created")

//     Try(Await.result(database.run(fieldTable), 5.seconds)) match
//         case Failure(exception) =>
//             println("Waiting for DB connection")
//             Thread.sleep(5000)
//             Await.result(database.run(fieldTable), 5.seconds)
//         case Success(value) => println("Field Tables created")

//     try {
//         Await.result(database.run(setup), 10.seconds)
//     } catch {
//         case e: SQLNonTransientException =>
//             println("Waiting for DB connection")
//             Thread.sleep(10000)
//             Await.result(database.run(setup), 10.seconds)
//     }
