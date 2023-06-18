<<<<<<< HEAD
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
=======
lazy val root = project
  .in(file("."))
  .settings(
    name         := "risk-game",
    version      := "0.1.0-SNAPSHOT",
    scalaVersion := "3.2.2",

    libraryDependencies += "org.scalactic"          %% "scalactic"   % "3.2.10",
    libraryDependencies += "org.scalatest"          %% "scalatest"   % "3.2.10" % "test",
    libraryDependencies += ("org.scalafx"           %% "scalafx"     % "17.0.1-R26").cross(CrossVersion.for3Use2_13),
    libraryDependencies += "com.google.inject"       % "guice"       % "4.2.3",
    libraryDependencies += ("net.codingwell"        %% "scala-guice" % "5.0.2").cross(CrossVersion.for3Use2_13),
    libraryDependencies += "org.scala-lang.modules" %% "scala-xml"   % "2.0.1",
    libraryDependencies += ("com.typesafe.play"     %% "play-json"   % "2.9.2").cross(CrossVersion.for3Use2_13),

    // Add JavaFX dependencies
    libraryDependencies ++= {
      // Determine OS version of JavaFX binaries
      lazy val osName = System.getProperty("os.name") match {
        case n if n.startsWith("Linux")   => "linux"
        case n if n.startsWith("Mac")     => "mac"
        case n if n.startsWith("Windows") => "win"
        case _                            => throw new Exception("Unknown platform!")
      }
      Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
        .map(m => "org.openjfx" % s"javafx-$m" % "16" classifier osName)
    },
>>>>>>> bb3297dadf4b39d8c1e06e168ebb092ef5152ed6

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
    name := "redesign-riskgame",
    commonSettings,

    Compile / sourceDirectory   := baseDirectory.value / "src/main",
    Compile / resourceDirectory := baseDirectory.value / "src/main/resources",
    Compile / target            := baseDirectory.value / "target",
    Test    / sourceDirectory   := baseDirectory.value / "src/test",
  )
  .aggregate(core, fileio, mapcreator)

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

lazy val fileio = project
  .in(file("fileio"))
  .settings(
    name         := "fileio",
    commonSettings,

    Compile / sourceDirectory   := baseDirectory.value / "fileio/src/main",
    Compile / resourceDirectory := baseDirectory.value / "fileio/src/main/resources",
    Compile / target            := baseDirectory.value / "fileio/target",
    Test    / sourceDirectory   := baseDirectory.value / "fileio/src/test",
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