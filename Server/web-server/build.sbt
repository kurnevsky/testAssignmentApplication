import Dependencies._

name := "web-server"
version := "0.1"
scalaVersion := "2.12.10"

lazy val root = Project("web-server", file("."))
  .settings(
    mainClass in(Compile, run) := Some("com.eg.assignment.server.Main"),
    mainClass in assembly := Some("com.eg.assignment.server.Main"),
    scalacOptions ++= Seq("-feature", "-deprecation", "-unchecked", "-Ypartial-unification", "-language:postfixOps"),
    assemblyOutputPath in assembly := baseDirectory.value /
      "target" / (name.value + "-" + version.value + ".jar"),
    libraryDependencies ++=
      Seq(
        activation,
        akkaHttp,
        akkaStream,
        dockerClient,
        scalaLogging,
        logback,
        commonLib,
        cats,
      ),
    test in assembly := {},
    assemblyMergeStrategy in assembly := {
      case PathList(ps @ _*) if ps.last endsWith "module-info.class" => MergeStrategy.first
      case PathList("javax", "inject", _ @ _*)                       => MergeStrategy.last
      case x                                                         =>
        val oldStrategy = (assemblyMergeStrategy in assembly).value
        oldStrategy(x)
    }
  )
