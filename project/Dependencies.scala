import sbt._

object Dependencies {
  lazy val scalaTest: ModuleID = "org.scalatest" %% "scalatest" % "3.0.3"
  lazy val scalaCheck: ModuleID = "org.scalacheck" %% "scalacheck" % "1.13.4"
}
