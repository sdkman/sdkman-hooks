import com.typesafe.config.ConfigFactory

enablePlugins(JavaServerAppPackaging)

enablePlugins(DockerPlugin)

name := """sdkman-hooks"""

val conf = ConfigFactory.parseFile(new File("conf/application.conf")).resolve()

version := conf.getString("application.version")

packageName in Docker := "sdkman/sdkman-hooks"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

routesGenerator := InjectedRoutesGenerator

resolvers ++= Seq(
  Resolver.bintrayRepo("sdkman", "maven"),
  Resolver.jcenterRepo
)

libraryDependencies ++= Seq(
  cache,
  ws,
  "io.sdkman" %% "sdkman-mongodb-persistence" % "0.9",
  "org.scalatest" %% "scalatest" % "3.0.0" % Test,
  "info.cukes" %% "cucumber-scala" % "1.2.5" % Test,
  "info.cukes" % "cucumber-junit" % "1.2.5" % Test,
  "org.scalaj" %% "scalaj-http" % "2.3.0" % Test,
  "com.github.tomakehurst" % "wiremock" % "2.2.2" % Test
)

