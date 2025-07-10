name := "my-scala-app"

version := "0.1"

ThisBuild / scalaVersion := "2.13.12"

libraryDependencies ++= Seq(
  "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-core" % "2.23.4",
  "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-macros" % "2.23.4" % Provided
)

