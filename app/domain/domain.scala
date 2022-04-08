package domain

case class Candidate private (identifier: String, name: String)
object Candidate {
  def apply(id: String): Candidate = Candidate(id, id.capitalize)

  val Flink  = Candidate("flink")
  val Hadoop = Candidate("hadoop")
  val Java   = Candidate("java")
  val Spark  = Candidate("spark")
  val JMC    = Candidate("jmc")
}

case class Platform(distribution: String, name: String, triple: Option[String] = None)

object Platform {

  def apply(platformId: String): Platform = platformId.toLowerCase match {
    // current
    case "linuxx64"       => LinuxX64
    case "linuxarm64"     => LinuxARM64
    case "linuxarm32sf"   => LinuxARM32SF
    case "linuxarm32hf"   => LinuxARM32HF
    case "darwinx64"      => MacX64
    case "darwinarm64"    => MacARM64
    case CygwinPattern(c) => Windows64
    // backward compat
    case "darwin"  => MacX64
    case "linux"   => LinuxX64
    case "linux64" => LinuxX64
    case "freebsd" => FreeBSD
    case "sunos"   => SunOS
    case _         => Exotic
  }

  private val CygwinPattern = "(cygwin|mingw64|msys).*".r

  val LinuxX32     = Platform("LINUX_32", "Linux 32bit")
  val LinuxX64     = Platform("LINUX_64", "Linux 64bit", Some("x86_64-unknown-linux-gnu"))
  val LinuxARM32SF = Platform("LINUX_ARM32SF", "Linux ARM 32bit Soft Float")
  val LinuxARM32HF = Platform("LINUX_ARM32HF", "Linux ARM 32bit Hard Float")
  val LinuxARM64   = Platform("LINUX_ARM64", "Linux ARM 64bit", Some("aarch64-unknown-linux-gnu"))
  val MacX64       = Platform("MAC_OSX", "macOS 64bit", Some("x86_64-apple-darwin"))
  val MacARM64     = Platform("MAC_ARM64", "macOS ARM 64bit", Some("aarch64-apple-darwin"))
  val Windows64    = Platform("WINDOWS_64", "Cygwin", Some("x86_64-pc-windows-msvc"))
  val FreeBSD      = Platform("FREE_BSD", "FreeBSD")
  val SunOS        = Platform("SUN_OS", "Solaris")

  //fallback to UNIVERSAL for exotic (unsupported) platforms
  //allows exotic platforms to install only UNIVERSAL candidates
  val Exotic = Platform("UNIVERSAL", "Exotic")
}

object JdkDistro {
  val AdoptOpenJDK = "adpt"
  val Amazon       = "amzn"
  val BellSoft     = "librca"
  val GraalVM      = "grl"
  val OpenJDK      = "open"
  val SapMachine   = "sapmchn"
  val Zulu         = "zulu"
  val ZuluFX       = "zulufx"
}

sealed trait Hooks {
  val phase: String
}
object Hooks {
  def from(phase: String) = phase match {
    case Post.phase => Post
    case Pre.phase  => Pre
  }
}
case object Post extends Hooks {
  override val phase = "post"
}
case object Pre extends Hooks {
  override val phase = "pre"
}
