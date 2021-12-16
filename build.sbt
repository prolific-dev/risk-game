import sbt.Keys.libraryDependencies

val scala3Version = "3.1.0"
val scalaTestVersion = "3.2.10"
val scalaFXVersion = "17.0.1-R26"
val javaFXVersion = "16"

lazy val root = project
  .in(file("."))
  .settings(
    name := "risk-game",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.10",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.10" % "test",
    libraryDependencies += "org.scalafx" %% "scalafx" % scalaFXVersion,

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



