import sbt._
import Keys._

object BuildSettings {
  val buildOrganization = "liberty"
  val buildVersion      = "0.0.1"
  val buildScalaVersion = "2.10.4"

  val buildSettings = Defaults.defaultSettings ++ Seq (
    organization := buildOrganization,
    version      := buildVersion,
    scalaVersion := buildScalaVersion
  )

}

object Resolvers {
  val mavenRepo = DefaultMavenRepository

  val libraryResolvers = Seq (mavenRepo)
}

object Dependencies {

  val ivyVersion        = "2.4.0"
  val zincVersion       = "0.3.7"
  val scalatestVersion  = "2.2.4"

  val ivyDependencyManager  = "org.apache.ivy" % "ivy" % ivyVersion
  val zincCompiler          = "com.typesafe.zinc" % "zinc" % zincVersion
  val scalatest             = "org.scalatest" % "scalatest_2.10" % scalatestVersion % "test"

}

object LibertyBuild extends Build {

  import Resolvers._
  import Dependencies._
  import BuildSettings._

  val coreDeps = Seq (
    ivyDependencyManager,
    zincCompiler,
    scalatest
  )

  lazy val liberty = Project (
    id        = "liberty", 
    base      = file("."),
    settings  = buildSettings ++ 
      Seq (resolvers := libraryResolvers, libraryDependencies ++= coreDeps)
  )

}
