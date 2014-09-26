name := "sigarz"

scalaVersion := "2.11.2"

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

// TODO Make work
//val nativeLibEnvVar = java.lang.System.getenv("SIGARZ_NATIVE_LIB_PATH")
//
//val defaultNativeLibDir = java.lang.System.getProperty("user.dir") + "/lib/ext"
//
//val nativeLibDir = if (nativeLibEnvVar != null) nativeLibEnvVar else defaultNativeLibDir
//
//val nativeLibPath = Seq(defaultNativeLibDir, nativeLibDir).mkString(java.io.File.pathSeparator)
//
//javaOptions in run += s"-Djava.library.path=${nativeLibPath}"
//
//javaOptions in test += s"-Djava.library.path=${nativeLibPath}"
//
//javaOptions in console += s"-Djava.library.path=${nativeLibPath}"
