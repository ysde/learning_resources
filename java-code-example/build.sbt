import sbt.Keys.version

name := "HikariCpDemo"

version := "1.0"

autoScalaLibrary := false

val coreDependencies = Seq(
  "com.zaxxer" % "HikariCP" % "3.3.1",
  "mysql" % "mysql-connector-java" % "8.0.12",
)

val testDependencies = Seq(
)

libraryDependencies ++= (coreDependencies ++ testDependencies)

