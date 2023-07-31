
name := "scala-testing-example"

version := "0.1"

scalaVersion := "2.13.11"

libraryDependencies ++=  Seq(
  "org.scalatest" %% "scalatest" % "3.2.15" % "test",
  "org.scalamock" %% "scalamock" % "5.2.0" % Test)
