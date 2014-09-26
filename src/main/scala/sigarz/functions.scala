package sigarz

import scalaz._
import Scalaz._
import scalaz.effect._

trait SigarzFunctions extends SigarzTypes {
  import org.hyperic.{sigar => S}

  def run[A, B, C](init: C => IO[B])(c: C)
                  (f: B => SigarzRunner[A]): SigarZ[A] =
    new SigarZ[A] {
      protected lazy val sigar = new S.Sigar

      def runSigar: SigarzRunner[A] = for {
        b <- init(c)
        sra <- f(b)
      } yield sra
    }

  def convertException[A](ex: Throwable): SigarzRunner[A] =
      SigarzError(ex).left[A].point[IO]

}
