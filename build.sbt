val scala3Version = "3.1.0"
val scalaTestVersion = "3.2.10"

lazy val root = project
  .in(file("."))
  .settings(
    name := "risk-game",
    version := "0.1.0-SNAPSHOT",
    //crossScalaVersions ++= Seq("3.1.0", "2.13.5"),
    scalaVersion := scala3Version,
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.10",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.10" % "test",
    libraryDependencies += ("org.scalafx" %% "scalafx" % "17.0.1-R26").cross(CrossVersion.for3Use2_13),
    libraryDependencies += "com.google.inject" % "guice"% "4.2.3",
    libraryDependencies += ("net.codingwell" %% "scala-guice" % "5.0.2").cross(CrossVersion.for3Use2_13),
    libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.0.1",
    libraryDependencies += ("com.typesafe.play" %% "play-json" % "2.9.2").cross(CrossVersion.for3Use2_13),
    //libraryDependencies += ("org.scalafx" %% "scalafx" % scalaFXVersion).cross(CrossVersion.for3Use2_13),

    // Add JavaFX dependencies
    libraryDependencies ++= {
      // Determine OS version of JavaFX binaries
      lazy val osName = System.getProperty("os.name") match {
        case n if n.startsWith("Linux") => "linux"
        case n if n.startsWith("Mac") => "mac"
        case n if n.startsWith("Windows") => "win"
        case _ => throw new Exception("Unknown platform!")
      }
      Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
        .map(m => "org.openjfx" % s"javafx-$m" % "16" classifier osName)
    },

    jacocoExcludes := Seq(
      "de.htwg.se.riskgame.RiskGame*",
      "de.htwg.se.riskgame.aview.gui.*"
    )

  )
  .enablePlugins(JacocoCoverallsPlugin)





