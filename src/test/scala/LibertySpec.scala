import liberty._

object ExampleLibrary extends App {
  val settings = Seq(
    Setting("organization" -> "com.example"),
    Setting("version" -> "0.1.0"),
    Setting("scalaVersion" -> "2.11.4")
  )
  val dependencies = Seq(
    Dependency("org.scala-lang.modules", "scala-async", "0.9.2"),
    Dependency("org.apache.spark", "apache-spark", "1.2.1")
  )

  val libertyContext = new LibertyContext(settings, dependencies)

  libertyContext.incrementallyCompile("src/main/scala", "target")
}

