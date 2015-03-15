Scala Library Build Tool Proof of Concept
=========================================
*Liberty* is a pure `Scala` library build tool. 
*Liberty* takes a different approach from `sbt`. While `sbt` calls your `build.sbt`/`Build.scala` configuration file,
*Liberty* provides a context API. 

You can use it by creating a *Liberty* context with dependencies/settings and then you can run the dependency analysis(`Apache Ivy`) and incremental compilation(Courtesy of `Zinc` Library). 

Status
------
Currently the source code demonstrates only the Interface. Future updates would be to complete `Apache Ivy` and 
`LibertySpecs`.

Interface
---------
See the specs below for how a use case would look like.
``` scala
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

  libertyContext.incrementallyCompile("src", "target")
}
```
