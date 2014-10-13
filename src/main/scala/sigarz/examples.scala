package sigarz

import scalaz._
import Scalaz._
import scalaz.effect._

import org.hyperic.{sigar => S}

object Examples extends SafeApp {
  import Sigarz._

  private val ENV_VAR = "SIGARZ_NATIVE_LIB_PATH"

  private def nativeLibPath: String =
    Option(java.lang.System.getenv(ENV_VAR)) | defaultNativeLibPath

  private def defaultNativeLibPath = Seq(
    System.getProperty("user.dir"),
    "lib",
    "ext"
  ).mkString(java.io.File.separator)

  // trivial example first
  private def init: Unit => IO[String] = Unit => IO {
    "sfsfsdfsdfsdfsfsdfdfsdfsdfs"
  }
  private def g: String => SigarzRunner[Int] = s => IO { s.length.right }
  private def sigarz = Sigarz.run(init)(nativeLibPath)(g)
  private def result = sigarz.runSigar

  private def init2: Unit => IO[S.Sigar] = Unit => IO {
    java.lang.System.setProperty("java.library.path", nativeLibPath)
    new S.Sigar
  }
  type AllTheInfos = (S.Cpu, Array[S.FileSystem], S.NetInfo, Array[S.NetRoute], S.NetStat)
  private def g2: S.Sigar => SigarzRunner[AllTheInfos] = s => IO {
    (s.getCpu, s.getFileSystemList, s.getNetInfo, s.getNetRouteList, s.getNetStat).right
  }
  private def sigarz2 = Sigarz.run(init2)(nativeLibPath)(g2)
  private def result2 = sigarz2.runSigar

  override def run(args: ImmutableArray[String]): IO[Unit] = for {
    i <- result
    _ <- IO.putStrLn(i.toString)

    c <- result2
    _ <- IO.putStrLn(c.toString)
  } yield ()

}
