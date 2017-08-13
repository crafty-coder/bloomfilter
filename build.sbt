import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "org.craftycoder",
      scalaVersion := "2.12.3",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "BloomFilterSet",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      scalaCheck % Test
    )

  )
testOptions in Test += Tests.Argument(TestFrameworks.ScalaCheck, "-minSuccessfulTests", "10000")
