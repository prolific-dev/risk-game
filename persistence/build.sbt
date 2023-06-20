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

lazy val persistence = project
  .in(file("."))
  .settings(commonSettings *)
  .settings(
    name := "persistence",
    libraryDependencies += ("com.typesafe.slick" %% "slick" % "3.5.0-M3").cross(CrossVersion.for3Use2_13),
    libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.32",
  )
