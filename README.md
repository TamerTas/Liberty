Scala Library Build Tool Proof of Concept
=========================================
Liberty is a pure Scala library build tool. Liberty takes a different approach from `sbt`. While `sbt` calls your `build.sbt`/`Build.scala` configuration file,
Liberty provides a context API. You can use it by creating a Liberty context with dependencies/settings and then you can run the dependency analysis(Apache Ivy) and 
incremental compilation(Courtesy of Zinc Library). Currently the source code demonstrates only the Interface. Future updates would be to complete Apache Ivy and 
LibertySpecs.
