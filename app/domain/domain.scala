package domain

case class Candidate private(identifier: String, name: String)
object Candidate {
  def apply(id: String): Candidate = Candidate(id, id.capitalize)

  val Java = Candidate("java")
}

case class Platform(identifier: String, name: String)
object Platform {

  def apply(id: String): Option[Platform] = id.toLowerCase match {
    case "darwin" => Some(MacOSX)
    case "freebsd" => Some(FreeBSD)
    case "sunos" => Some(SunOS)
    case LinuxPattern(_) => Some(Linux)
    case WindowsCygwinPattern(_) => Some(Windows64Cygwin)
    case WindowsUnsupportedPattern(_) => Some(Windows64Unsupported)
    case _ => None
  }

  private val WindowsCygwinPattern = "(cygwin).*".r

  private val WindowsUnsupportedPattern = "(mingw|msys).*".r

  private val LinuxPattern = "(linux|linux32|linux64)".r

  val Linux = Platform("LINUX", "Linux")
  val MacOSX = Platform("MAC_OSX", "Mac OSX")
  val Windows64Cygwin = Platform("CYGWIN", "Cygwin")
  val Windows64Unsupported = Platform("UNSUPPORTED", "Unsupported")
  val FreeBSD = Platform("FREE_BSD", "FreeBSD")
  val SunOS = Platform("SUN_OS", "Solaris")
  val Universal = Platform("UNIVERSAL", "Universal")
}