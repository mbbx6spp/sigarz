name := "sigarz"

scalaVersion := "2.11.3"

libraryDependencies ++= {
  val scalazVersion = "7.1.0"
  //val scalazStreamVersion = "0.5a"
  val scalacheckVersion = "1.11.5"
  val sigarVersion = "1.6.4"
  Seq(
    "org.scalaz"        %% "scalaz-core" % scalazVersion,
    "org.scalaz"        %% "scalaz-effect" % scalazVersion,
    "org.scalaz"        %% "scalaz-concurrent" % scalazVersion,
    "org.fusesource"    %  "sigar" % sigarVersion,
    //"org.scalaz.stream" %% "scalaz-stream" % scalazStreamVersion,
    //"io.argonaut"       %% "argonaut" % "6.0.4",
    "org.scalacheck"    %% "scalacheck" % scalacheckVersion % "test",
    "org.scalaz"        %% "scalaz-scalacheck-binding" % scalazVersion % "test"
  )
}

resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"

scalacOptions += "-feature"

scalacOptions += "-unchecked"

initialCommands in console := "import scalaz._, Scalaz._, scalaz.effect._"

releaseSettings
