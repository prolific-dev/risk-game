val scala3Version   = "3.2.2"
val AkkaHttpVersion = "10.5.1"

lazy val commonSettings = Seq(
  scalaVersion := scala3Version,

  libraryDependencies += "org.scalameta" %% "munit"     % "0.7.29" % Test,
  libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.15",
  libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.15" % "test",
  libraryDependencies += "org.scalafx"   %% "scalafx"   % "20.0.0-R31",

  libraryDependencies += ("com.typesafe.play" %% "play-json" % "2.9.2").cross(CrossVersion.for3Use2_13),

  libraryDependencies += "com.typesafe.akka" %% "akka-http"            % "10.5.1",
  libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json" % "10.5.1",
  libraryDependencies += "com.typesafe.akka" %% "akka-actor"           % "2.8.0",
  libraryDependencies += "com.typesafe.akka" %% "akka-actor-typed"     % "2.8.0",
  libraryDependencies += "com.typesafe.akka" %% "akka-stream"          % "2.8.0",

  libraryDependencies += ("com.typesafe.slick" %% "slick" % "3.5.0-M3").cross(CrossVersion.for3Use2_13),

  libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.32",

    jacocoExcludes := Seq(
    "de.htwg.se.riskgame.RiskGame*",
    "de.htwg.se.riskgame.aview.gui.*",
    "de.htwg.se.riskgame.model.fileIoComponent.*",
  )
)

lazy val root = project
  .in(file("."))
  .enablePlugins(JacocoCoverallsPlugin, DockerPlugin, JavaAppPackaging)
  .settings(
    name := "riskgame",
    commonSettings,

    Compile / sourceDirectory   := baseDirectory.value / "src/main",
    Compile / resourceDirectory := baseDirectory.value / "src/main/resources",
    Compile / target            := baseDirectory.value / "target",
    Test    / sourceDirectory   := baseDirectory.value / "src/test",
  )
  .aggregate(core, persistence, mapcreator)

lazy val core = project
  .in(file("core"))
  .settings(
    name := "core",
    commonSettings,

    Compile / sourceDirectory   := baseDirectory.value / "core/src/main",
    Compile / resourceDirectory := baseDirectory.value / "core/src/main/resources",
    Compile / target            := baseDirectory.value / "core/target",
    Test    / sourceDirectory   := baseDirectory.value / "core/src/test",
  )

lazy val persistence = project
  .in(file("persistence"))
  .settings(
    name         := "persistence",
    commonSettings,

    Compile / sourceDirectory   := baseDirectory.value / "persistence/src/main",
    Compile / resourceDirectory := baseDirectory.value / "persistence/src/main/resources",
    Compile / target            := baseDirectory.value / "persistence/target",
    Test    / sourceDirectory   := baseDirectory.value / "persistence/src/test",
  )

lazy val mapcreator = project
  .in(file("mapcreator"))
  .settings(
    name         := "mapcreator",
    organization := "de.htwg.se.riskgame.mapcreator",
    commonSettings,

    Compile / sourceDirectory   := baseDirectory.value / "mapcreator/src/main",
    Compile / resourceDirectory := baseDirectory.value / "mapcreator/src/main/resources",
    Compile / target            := baseDirectory.value / "mapcreator/target",
    Test    / sourceDirectory   := baseDirectory.value / "mapcreator/src/test",
  )