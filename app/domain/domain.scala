package domain

case class Candidate private(identifier: String, name: String)
object Candidate {
  def apply(id: String): Candidate = Candidate(id, id.capitalize)

  val Java = Candidate("java")

  val Spark = Candidate("spark")
}

case class Platform(identifier: String, name: String)
object Platform {

  def apply(id: String): Option[Platform] = id.toLowerCase match {
    case "darwin" => Some(MacOSX)
    case "freebsd" => Some(FreeBSD)
    case "sunos" => Some(SunOS)
    case LinuxPattern(_) => Some(Linux)
    case WindowsCygwinPattern(_) => Some(Windows64Cygwin)
    case WindowsMinGWPattern(_) => Some(Windows64MinGW)
    case _ => None
  }

  private val WindowsCygwinPattern = "(cygwin).*".r

  private val WindowsMinGWPattern = "(mingw|msys).*".r

  private val LinuxPattern = "(linux|linux32|linux64)".r

  val Linux = Platform("LINUX", "Linux")
  val MacOSX = Platform("MAC_OSX", "Mac OSX")
  val Windows64Cygwin = Platform("CYGWIN", "Cygwin")
  val Windows64MinGW = Platform("MINGW", "MinGW")
  val FreeBSD = Platform("FREE_BSD", "FreeBSD")
  val SunOS = Platform("SUN_OS", "Solaris")
  val Universal = Platform("UNIVERSAL", "Universal")
}

object JdkDistro {
  val AdoptOpenJDK = "adopt"
  val Amazon = "amzn"
  val GraalVM = "grl"
  val OpenJDK = "open"
  val Oracle = "oracle"
  val Zulu = "zulu"
  val ZuluFX = "zulufx"
}