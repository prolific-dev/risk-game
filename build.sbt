val scala3Version   = "3.2.2"
val AkkaHttpVersion = "10.5.1"

lazy val commonSettings = Seq(
  scalaVersion := scala3Version,

  libraryDependencies += "org.scalameta"      %% "munit"                % "0.7.29" % Test,
  libraryDependencies += "org.scalactic"      %% "scalactic"            % "3.2.15",
  libraryDependencies += "org.scalatest"      %% "scalatest"            % "3.2.15" % "test",
  libraryDependencies += ("com.typesafe.play" %% "play-json"            % "2.9.2").cross(CrossVersion.for3Use2_13),
  libraryDependencies += "com.typesafe.akka"  %% "akka-http"            % "10.5.1",
  libraryDependencies += "com.typesafe.akka"  %% "akka-http-spray-json" % "10.5.1",
  libraryDependencies += "com.typesafe.akka"  %% "akka-actor"           % "2.8.0",
  libraryDependencies += "com.typesafe.akka"  %% "akka-actor-typed"     % "2.8.0",
  libraryDependencies += "com.typesafe.akka"  %% "akka-stream"          % "2.8.0",
)

lazy val root = project
  .in(file("."))
  .aggregate(core, persistence, mapcreator)
  .enablePlugins(JacocoCoverallsPlugin, DockerPlugin, JavaAppPackaging)
  .settings(commonSettings: _*)
  .settings(
    name := "riskgame",
  )

lazy val core = project
  .in(file("core"))
  .enablePlugins(JacocoCoverallsPlugin, DockerPlugin, JavaAppPackaging)
  .settings(commonSettings: _*)
  .settings(
    name := "core",
    libraryDependencies += "org.scalafx" %% "scalafx" % "20.0.0-R31",
    jacocoExcludes := Seq(
      "de.htwg.se.riskgame.RiskGame*",
      "de.htwg.se.riskgame.aview.gui.*",
      "de.htwg.se.riskgame.model.fileIoComponent.*",
    )
  )

lazy val persistence = project
  .in(file("persistence"))
  .enablePlugins(JacocoCoverallsPlugin, DockerPlugin, JavaAppPackaging)
  .settings(commonSettings: _*)
  .settings(
    name := "persistence",
    libraryDependencies += ("com.typesafe.slick" %% "slick" % "3.5.0-M3").cross(CrossVersion.for3Use2_13),
    libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.32",
    libraryDependencies += ("org.mongodb.scala" %% "mongo-scala-driver" % "4.6.0").cross(CrossVersion.for3Use2_13),
  )

lazy val mapcreator = project
  .in(file("mapcreator"))
  .enablePlugins(JacocoCoverallsPlugin, DockerPlugin, JavaAppPackaging)
  .settings(commonSettings: _*)
  .settings(
    name := "mapcreator",
  )
