import com.typesafe.config.ConfigFactory

enablePlugins(JavaServerAppPackaging)

enablePlugins(DockerPlugin)

name := """sdkman-hooks"""

val conf = ConfigFactory.parseFile(new File("conf/application.conf")).resolve()

version := conf.getString("application.version")

Docker / packageName := "sdkman/sdkman-hooks"

dockerBaseImage := "openjdk:11"

Universal / javaOptions ++= Seq(
  "-Dpidfile.path=/dev/null"
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.13"

routesGenerator := InjectedRoutesGenerator

resolvers ++= Seq(
  Resolver.mavenCentral,
  "jitpack" at "https://jitpack.io"
)

libraryDependencies ++= Seq(
  guice,
  ws,
  "com.github.sdkman" % "sdkman-mongodb-persistence" % "1.9",
  "org.scalatest" %% "scalatest" % "3.0.0" % Test,
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
  "io.cucumber" %% "cucumber-scala" % "4.7.1" % Test,
  "io.cucumber" % "cucumber-junit" % "4.7.1" % Test,
  "info.cukes" % "gherkin" % "2.7.3" % Test,
  "org.scalaj" %% "scalaj-http" % "2.3.0" % Test,
  "com.github.tomakehurst" % "wiremock" % "2.2.2" % Test
)

import sbtrelease.ReleasePlugin.autoImport.ReleaseTransformations._

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  ReleaseStep(releaseStepTask(publish in Docker)),
  setNextVersion,
  commitNextVersion,
  pushChanges
)
