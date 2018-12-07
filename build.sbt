name := "FarmDiagnostics"
 
version := "1.0" 
      
lazy val `farmdiagnostics` = (project in file(".")).enablePlugins(PlayScala)

lazy val dl4j =
  project
    .in(file("dl4j"))
    .settings(settings)
    .settings(
      scalaVersion := "2.11.12", // ScalNet and ND4S are only available for Scala 2.11
      libraryDependencies ++= Seq(
        library.dl4j,
        library.dl4jCuda,
        library.dl4jUi,
        library.logbackClassic,
        library.nd4jNativePlatform,
        library.scalNet
      )
    )

lazy val tensorFlow =
  project
    .in(file("tensorflow"))
    .settings(settings)
    .settings(
     
      libraryDependencies ++= Seq(
        library.betterFiles,
        library.janino,
        library.logbackClassic,
        library.tensorFlow,
        library.tensorFlowData
      ),
      fork := true // prevent classloader issues caused by sbt and opencv
    )

// *****************************************************************************
// Library dependencies
// *****************************************************************************

lazy val library =
  new {
    object Version {
      val betterFiles = "3.4.0"
      val dl4j = "1.0.0-alpha"
      val janino = "2.6.1"
      val logbackClassic = "1.2.3"
      val scalaCheck = "1.13.5"
      val scalaTest  = "3.0.4"
      val tensorFlow = "0.2.4"

    }
    val betterFiles = "com.github.pathikrit" %% "better-files" % Version.betterFiles
    val dl4j = "org.deeplearning4j" % "deeplearning4j-core" % Version.dl4j
    val dl4jUi = "org.deeplearning4j" %% "deeplearning4j-ui" % Version.dl4j
    val janino = "org.codehaus.janino" % "janino" % Version.janino
    val logbackClassic = "ch.qos.logback" % "logback-classic" % Version.logbackClassic
    val nd4jNativePlatform = "org.nd4j" % "nd4j-cuda-9.0-platform" % Version.dl4j
    val dl4jCuda = "org.deeplearning4j" % "deeplearning4j-cuda-9.0" % Version.dl4j
    val scalaCheck = "org.scalacheck" %% "scalacheck" % Version.scalaCheck
    val scalaTest  = "org.scalatest"  %% "scalatest"  % Version.scalaTest
    val scalNet = "org.deeplearning4j" %% "scalnet" % Version.dl4j
    // change the classifier to "linux-cpu-x86_64" or "linux-gpu-x86_64" if you're on a linux/linux with nvidia system
    val tensorFlow = "org.platanios" %% "tensorflow" % Version.tensorFlow classifier "darwin-cpu-x86_64"
    val tensorFlowData = "org.platanios" %% "tensorflow-data" % Version.tensorFlow
  }

// *****************************************************************************
// Settings
// *****************************************************************************

lazy val settings =
  Seq(
    scalaVersion := "2.12.6",
    organization := "io.brunk",
    organizationName := "Sören Brunk",
    startYear := Some(2017),
    licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0")),
    scalacOptions ++= Seq(
      "-unchecked",
      "-deprecation",
      "-language:_",
      "-target:jvm-1.8",
      "-encoding", "UTF-8"
    ),
    unmanagedSourceDirectories.in(Compile) := Seq(scalaSource.in(Compile).value),
    unmanagedSourceDirectories.in(Test) := Seq(scalaSource.in(Test).value),
    resolvers ++= Seq(
      Resolver.sonatypeRepo("snapshots")
    )
  )

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  "com.h2database" % "h2" % "1.4.188",
  "com.typesafe.play" %% "play-slick" % "3.0.3",
  "com.typesafe.play" %% "play-slick-evolutions" % "3.0.3",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.1",
  "org.scalaz" %% "scalaz-core" % "7.2.27",
  "org.scalaz" %% "scalaz-concurrent" % "7.2.27",
  "org.scalaz" %% "scalaz-effect" % "7.2.27",
  "org.postgresql" % "postgresql" % "42.1.4",
  "com.typesafe.akka" %% "akka-stream" % "2.5.8",
  "com.typesafe.akka" %% "akka-stream-kafka" % "0.22",
  ehcache ,
  ws ,
  specs2 % Test,
  guice)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )
