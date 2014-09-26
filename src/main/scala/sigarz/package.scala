package sigarz

import scalaz._
import Scalaz._
import scalaz.effect._

import org.hyperic.sigar.Sigar


object Sigarz extends App {

  val SIGARZ_NATIVE_LIB_PATH = "SIGARZ_NATIVE_LIB_PATH"

  def configure(libExtPath: String = java.lang.System.getenv(SIGARZ_NATIVE_LIB_PATH)): Sigar = {
    java.lang.System.setProperty("java.library.path", libExtPath)
    new Sigar
  }

  val sigar = configure()
  println(sigar.getCpu)
  println(sigar.getCpuList)

}
