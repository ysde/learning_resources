import scala.util.Try

import sbt.Keys.version

name := "sbt-sample"

version := "0.1"

scalaVersion := "2.12.4"

wartremoverErrors ++= Warts.unsafe

val coreDependencies = Seq(
  "org.scalactic" %% "scalactic" % "3.0.5",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.8.0",
  "com.typesafe" % "config" % "1.3.2"
)

val testDependencies = Seq(
  "org.scalatest" %% "scalatest" % "3.0.5" % "test"
)

scalacOptions += "-Ypartial-unification"

libraryDependencies ++= (coreDependencies ++ testDependencies)

coverageEnabled in(Test, compile) := true
coverageMinimum := 80
coverageFailOnMinimum := true
//coverage test command: sbt clean coverage test coverageReport

// Docker packaging
// https://www.scala-sbt.org/sbt-native-packager/formats/docker.html#
enablePlugins(DockerPlugin)
enablePlugins(AshScriptPlugin) // needed by alpine
dockerBaseImage := "openjdk:jre-alpine" // cannot be used when including pub/sub sdk

val imageUrl = Try(scala.sys.env("IMAGE_URL")).toOption
val projectName = Try(scala.sys.env("PROJECT_NAME")).toOption
val buildVersion = Try(scala.sys.env("BUILD_VERSION")).toOption
version in Docker := buildVersion.getOrElse("0.1")
packageName in Docker := projectName.getOrElse("sbt-sample")
dockerRepository := imageUrl
