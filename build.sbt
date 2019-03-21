name := "FarmDiagnostics"
 
version := "1.0" 
      
lazy val `farmdiagnostics` = (project in file(".")).enablePlugins(PlayScala, PlayJava)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
resolvers += Resolver.bintrayRepo("cibotech", "public")

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  "com.h2database" % "h2" % "1.4.188",
  "com.typesafe.play" %% "play-slick" % "3.0.3",
  "com.typesafe.play" %% "play-slick-evolutions" % "3.0.3",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.1",
  "com.typesafe.play" %% "play" % "2.6.20",
  "com.typesafe.play" %% "play-ws" % "2.6.20",
  "com.typesafe.play" %% "play-json" % "2.6.11",
  "org.scalaz" %% "scalaz-core" % "7.2.27",
  "org.scalaz" %% "scalaz-concurrent" % "7.2.27",
  "org.scalaz" %% "scalaz-effect" % "7.2.27",
  "org.postgresql" % "postgresql" % "42.1.4",
  "com.typesafe.akka" %% "akka-stream" % "2.5.8",
  "com.typesafe.akka" %% "akka-stream-kafka" % "0.22",
  "org.apache.flink" %% "flink-streaming-scala" % "1.7.1",
  "org.apache.flink" %% "flink-scala" % "1.7.1",
  "org.apache.flink" % "flink-core" % "1.7.1",
  "org.apache.flink" % "flink-examples" % "1.7.1" pomOnly(),
  "org.apache.flink" % "flink-contrib" % "1.7.1" pomOnly(),
  "org.apache.kafka" %% "kafka" % "2.1.0",
  "org.apache.flink" %% "flink-connector-kafka-0.9" % "1.7.1",
  "com.cibo" %% "scalastan" % "0.8.1",
  "com.google.api-client" % "google-api-client" % "1.27.0",
  "com.google.oauth-client" % "google-oauth-client" % "1.27.0",
  "com.google.apis" % "google-api-services-drive" % "v3-rev136-1.25.0",
  "com.google.api-client" % "google-api-client-java6" % "1.27.0",
  "com.google.oauth-client" % "google-oauth-client-jetty" % "1.27.0",
  "com.google.api.client" % "google-api-client-extensions" % "1.4.1-beta",
  "com.stripe" %% "rainier-core" % "0.2.0",
  "org.deeplearning4j" % "deeplearning4j-core" % "0.0.3.1",
  "org.nd4j" % "nd4j" % "0.9.1" pomOnly(),
  "org.apache.spark" %% "spark-core" % "2.4.0",
  "org.apache.spark" %% "spark-sql" % "2.4.0",
  "org.apache.spark" %% "spark-mllib" % "2.4.0" % "runtime",
  "org.apache.spark" %% "spark-streaming" % "2.4.0" % "provided",
  "org.apache.samza" % "samza-api" % "1.0.0",
  "org.boofcv" % "boofcv-core" % "0.27",
  "org.tensorflow" % "tensorflow" % "1.12.0",
  ehcache ,
  ws ,
  specs2 % Test,
  guice)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )
