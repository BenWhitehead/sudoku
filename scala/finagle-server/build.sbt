organization := "io.github.benwhitehead.sudoku"

name := "sudoku-finagle-server"

version := "0.1-SNAPSHOT"

scalaVersion := "2.10.3"

scalacOptions ++= Seq("-unchecked", "-deprecation")

javacOptions ++= Seq("-source", "1.7", "-target", "1.7")

javacOptions in doc := Seq("-source", "1.7")

resolvers += "Twitter" at "http://maven.twttr.com/"

resolvers += "Finch.io" at "http://repo.konfettin.ru"

resolvers += Resolver.mavenLocal

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic"  % "1.1.2",
  "io.github.benwhitehead.finch" %% "finch-server" % "0.1-SNAPSHOT",
  "com.github.benwhitehead.tutorials.sudoku" % "sudoku" % "1.0-SNAPSHOT"
)

parallelExecution in Test := true

