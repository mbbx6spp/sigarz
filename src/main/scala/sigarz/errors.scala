package sigarz

trait SigarzErrors {
  case class MalformedQueryError(msg: String) extends SigarzError
  case class NfsUnreachableError(msg: String) extends SigarzError
  case class FileNotFoundError(msg: String) extends SigarzError
  case class NotImplementedError(msg: String) extends SigarzError
  case class PermissionDeniedError(msg: String) extends SigarzError
  case class VMWareError(msg: String) extends SigarzError
  case class Win32Error(msg: String) extends SigarzError
  case class UnknownError(msg: String) extends SigarzError

  sealed trait SigarzError
  object SigarzError {
    import org.hyperic.{sigar => S}

    def apply(e: Throwable): SigarzError = e match {
      case _: S.ptql.MalformedQueryException    => MalformedQueryError(e.getMessage)
      case _: S.NfsUnreachableException         => NfsUnreachableError(e.getMessage)
      case _: S.SigarFileNotFoundException      => FileNotFoundError(e.getMessage)
      case _: S.SigarNotImplementedException    => NotImplementedError(e.getMessage)
      case _: S.SigarPermissionDeniedException  => PermissionDeniedError(e.getMessage)
      case _: S.vmware.VMwareException          => VMWareError(e.getMessage)
      case _: S.win32.Win32Exception            => Win32Error(e.getMessage)
      case _                                    => UnknownError(e.getMessage)
    }
  }
}
