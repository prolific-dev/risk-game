lazy val root = project
  .in(file("."))
  .settings(
    name := "risk-game",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.10",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.10" % "test"
  )
  .enablePlugins(JacocoCoverallsPlugin)

jacocoExcludes := Seq(
  "de.htwg.se.riskgame.RiskGame.*"
)

val scala3Version = "3.1.0"
val scalaTestVersion = "3.2.10"
