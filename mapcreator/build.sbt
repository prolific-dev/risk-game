val scala3Version   = "3.2.2"
val AkkaHttpVersion = "10.5.1"

lazy val commonSettings = Seq(
  scalaVersion := scala3Version,

  libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test,
  libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.15",
  libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.15" % "test",
  libraryDependencies += "org.slf4j" % "slf4j-nop" % "1.7.25",
  libraryDependencies += ("com.typesafe.play" %% "play-json" % "2.9.2").cross(CrossVersion.for3Use2_13),
  libraryDependencies += "com.typesafe.akka" %% "akka-http" % "10.5.1",
  libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json" % "10.5.1",
  libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.8.0",
  libraryDependencies += "com.typesafe.akka" %% "akka-actor-typed" % "2.8.0",
  libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.8.0",
  libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % "3.9.5" exclude("com.typesafe.scala-logging", "scala-logging_2.13"),
  libraryDependencies += "io.gatling" % "gatling-test-framework" % "3.9.5" exclude("com.typesafe.scala-logging", "scala-logging_2.13"),
)
lazy val mapcreator = project
  .in(file("."))
  .enablePlugins(JacocoCoverallsPlugin, DockerPlugin, JavaAppPackaging)
  .settings(commonSettings *)
  .settings(
    name := "mapcreator",
  )
