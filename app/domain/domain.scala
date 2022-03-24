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

case class Platform(identifier: String, name: String)
object Platform {

  def apply(id: String): Platform = id.toLowerCase match {
    case LinuxPattern(_)         => Linux
    case DarwinPattern(_)        => MacOSX
    case WindowsCygwinPattern(_) => Windows64Cygwin
    case WindowsMinGWPattern(_)  => Windows64MinGW
    case "freebsd"               => FreeBSD
    case "sunos"                 => SunOS
    case _                       => Exotic
  }

  private val WindowsCygwinPattern = "(cygwin).*".r

  private val WindowsMinGWPattern = "(mingw64|msys).*".r

  private val LinuxPattern =
    "(linux|linux64|linuxx64|linuxarm32|linuxarm64|linuxarm32sf|linuxarm32hf)".r

  private val DarwinPattern = "(darwin|darwinx64|darwinarm64)".r

  val Linux           = Platform("LINUX", "Linux")
  val MacOSX          = Platform("MAC_OSX", "Mac OSX")
  val Windows64Cygwin = Platform("CYGWIN", "Cygwin")
  val Windows64MinGW  = Platform("MINGW", "MinGW")
  val FreeBSD         = Platform("FREE_BSD", "FreeBSD")
  val SunOS           = Platform("SUN_OS", "Solaris")
  val Exotic          = Platform("UNIVERSAL", "Universal")
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
