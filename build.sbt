name := "PermCombRepo"

version := "1.0"

scalaVersion := "2.11.6"

autoCompilerPlugins := true

addCompilerPlugin("org.scala-lang.plugins" % "scala-continuations-plugin_2.11.6" % "1.0.2")

libraryDependencies += "org.scala-lang.plugins" %% "scala-continuations-library" % "1.0.2"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "3.0.0" % "test"

scalacOptions += "-P:continuations:enable"