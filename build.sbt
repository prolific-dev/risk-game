import sbt.Keys.libraryDependencies

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
    libraryDependencies += "org.scalafx" %% "scalafx" % scalaFXVersion,
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
        .map(m => "org.openjfx" % s"javafx-$m" % javaFXVersion classifier osName)
    }
  )
  .enablePlugins(JacocoCoverallsPlugin)
val javaFXVersion = "16"
val scalaFXVersion = "16.0.0-R24"




