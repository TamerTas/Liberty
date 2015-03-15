// Library Build Tool
package liberty

import java.io.File
import scala.tools.nsc.io.{ Directory, Path }
import com.typesafe.zinc.{ Compiler, Setup, Inputs }
import Setup.{ Defaults, zincVersion }
import org.apache.ivy._
import sbt._
import xsbti.Logger

trait Setting {
  def apply()
}

trait Dependency {
  def resolve()
}

abstract class LibertyContext(settings: Seq[Setting], dependencies: Seq[Dependency]) {
  val scalaCompiler             = Defaults.scalaCompiler.getAbsoluteFile
  val scalaLibrary              = Defaults.scalaLibrary.getAbsoluteFile
  val scalaReflect              = Defaults.scalaExtra.map(_.getAbsoluteFile)
  val sbtInterface              = Defaults.sbtInterface.getAbsoluteFile
  val compilerInterfaceSrc      = Defaults.compilerInterfaceSrc.getAbsoluteFile
  val javaHome                  = new File("/usr/lib/jvm/java-7-oracle")
  val forkJava                  = true
  val cacheDirectory            = Defaults.zincDir.getAbsoluteFile

  val zincSetup = Setup(scalaCompiler, scalaLibrary, scalaReflect, 
                        sbtInterface, compilerInterfaceSrc, Option(javaHome), 
                        forkJava, cacheDirectory)

  val zincCompilerLogger = new ZincLogger

  val zincCompiler = Compiler.create(zincSetup, zincCompilerLogger)

  def getInputs(src: String, target: String): Inputs

  def incrementalyCompile(src: String, target: String) = {
    val srcDirectory = new Directory(new File(src))

    val sources = srcDirectory.deepFiles.map {
      file =>
        if (file.hasExtension("scala")) {
          file.jfile 
        } else {
          file
        }
    }

    zincCompiler.compile(getInputs(src, target))(zincCompilerLogger)
  }

  class ZincLogger extends Logger {
    val logger = zincCompilerLogger
    def trace(arg: xsbti.F0[Throwable])  { logger.trace(arg)  }
    def info(arg: xsbti.F0[String])      { logger.info(arg)   }
    def debug(arg: xsbti.F0[String])     { logger.debug(arg)  }
    def warn(arg: xsbti.F0[String])      { logger.warn(arg)   }
    def error(arg: xsbti.F0[String])     { logger.error(arg)  }
  }
}
