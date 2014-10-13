package sigarz

import scalaz._
import Scalaz._
import scalaz.effect._

trait SigarzTypes extends SigarzErrors {
  import org.hyperic.{sigar => S}

  abstract class SigarZ[A] {
    protected val sigar: S.Sigar
    def runSigar: SigarzRunner[A]
  }

  type SigarzRunner[A] = IO[SigarzError \/ A]

  /** Actions **/



  /** Basic Data Types **/

  // Not need for Cpu and CpuPerc. What were they thinking?
  case class CpuUsage(
    idle: Long
  , irq: Long
  , nice: Long
  , stolen: Long
  , sys: Long
  , total: Long
  , user: Long
  , ioWait: Long
  ) {
    def idlePerc: Double    = mkPerc(idle)
    def irqPerc: Double     = mkPerc(irq)
    def nicePerc: Double    = mkPerc(nice)
    def stolenPerc: Double  = mkPerc(stolen)
    def sysPerc: Double     = mkPerc(sys)
    def userPerc: Double    = mkPerc(user)
    def ioWaitPerc: Double  = mkPerc(ioWait)
    private def mkPerc: Long => Double = l => (l / total)*100
  }
  object CpuUsage {
    def apply(cu: S.Cpu): CpuUsage = CpuUsage(
      cu.getIdle
    , cu.getIrq
    , cu.getNice
    , cu.getStolen
    , cu.getSys
    , cu.getTotal
    , cu.getUser
    , cu.getWait
    )
  }
  type CpuUsageZ = SigarzRunner[CpuUsage]

  case class CpuInfo(
    cacheSize: Long
  , coresPerSocket: Int
  , mhz: Int
  , model: String /*candidate for sum type?*/
  , totalCores: Int
  , totalSockets: Int
  , vendor: String /*candidate for sum type?*/
  )
  object CpuInfo {
    def apply(ci: S.CpuInfo): CpuInfo = CpuInfo(
      ci.getCacheSize
    , ci.getCoresPerSocket
    , ci.getMhz
    , ci.getModel
    , ci.getTotalCores
    , ci.getTotalSockets
    , ci.getVendor
    )
  }
  type CpuInfoZ = SigarzRunner[CpuInfo]

  case class DirStat(
    blkdevs: Long
  , chrdevs: Long
  , diskUsage: Long
  , sockets: Long
  , files: Long
  , subdirs: Long
  , symlinks: Long
  , total: Long
  )
  object DirStat {
    def apply(ds: S.DirStat): DirStat = DirStat(
      ds.getBlkdevs
    , ds.getChrdevs
    , ds.getDiskUsage
    , ds.getSockets
    , ds.getFiles
    , ds.getSubdirs
    , ds.getSymlinks
    , ds.getTotal
    )
  }
  type DirStatZ = SigarzRunner[DirStat]

  case class DiskUsage(
    queue: Double
  , readBytes: Long
  , reads: Long
  , serviceTime: Double
  , writeBytes: Double
  , writes: Long
  )
  object DiskUsage {
    def apply(du: S.DiskUsage): DiskUsage = DiskUsage(
      du.getQueue
    , du.getReadBytes
    , du.getReads
    , du.getServiceTime
    , du.getWriteBytes
    , du.getWrites
    )
  }
  type DiskUsageZ = SigarzRunner[DiskUsage]

  case class FileAttrs(
    atime: Long
  , ctime: Long
  , device: Long
  , gid: Long
  , inode: Long
  , mtime: Long
  , nlink: Long
  , permissions: Long
  , size: Long
  , fileType: Int /* sum type candidate */
  , uid: Long
  )
  object FileAttrs {
    def apply(fa: S.FileAttrs): FileAttrs = FileAttrs(
      fa.getAtime
    , fa.getCtime
    , fa.getDevice
    , fa.getGid
    , fa.getInode
    , fa.getMtime
    , fa.getNlink
    , fa.getPermissions
    , fa.getSize
    , fa.getType
    , fa.getUid
    )
  }
  type FileAttrsZ = SigarzRunner[FileAttrs]


  case class FileSystem(
    devName: String
  , dirName: String
  , flags: Long
  , options: String
  , sysTypeName: String
  , typeName: String /* candidate for sum type */
  )
  object FileSystem {
    def apply(fs: S.FileSystem): FileSystem = FileSystem(
      fs.getDevName
    , fs.getDirName
    , fs.getFlags
    , fs.getOptions
    , fs.getSysTypeName
    , fs.getTypeName
    )
  }
  type FileSystemZ = SigarzRunner[FileSystem]

  case class FileSystemUsage(
    avail: Long
  , diskQueue: Double
  , diskReadBytes: Long
  , diskReads: Long
  , diskServiceTime: Double
  , diskWriteBytes: Long
  , diskWrites: Long
  , files: Long
  , free: Long
  , freeFiles: Long
  , total: Long
  , used: Long
  , usePercent: Double
  )
  object FileSystemUsage {
    def apply(fsu: S.FileSystemUsage): FileSystemUsage = FileSystemUsage(
      fsu.getAvail
    , fsu.getDiskQueue
    , fsu.getDiskReadBytes
    , fsu.getDiskReads
    , fsu.getDiskServiceTime
    , fsu.getDiskWriteBytes
    , fsu.getDiskWrites
    , fsu.getFiles
    , fsu.getFree
    , fsu.getFreeFiles
    , fsu.getTotal
    , fsu.getUsed
    , fsu.getUsePercent
    )
  }
  type FileSystemUsageZ = SigarzRunner[FileSystemUsage]

  case class Mem(
    actualFree: Long
  , actualUsed: Long
  , free: Long
  , freePercent: Double
  , ram: Long
  , total: Long
  , used: Long
  , usedPercent: Double
  )
  object Mem {
    def apply(m: S.Mem): Mem = Mem(
      m.getActualFree
    , m.getActualUsed
    , m.getFree
    , m.getFreePercent
    , m.getRam
    , m.getTotal
    , m.getUsed
    , m.getUsedPercent
    )
  }
  type MemZ = SigarzRunner[Mem]
}
