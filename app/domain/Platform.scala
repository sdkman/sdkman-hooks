package domain

case class Platform(identifier: String, name: String)

object Platform {

  def apply(id: String): Option[Platform] = id.toLowerCase match {
    case "darwin" => Some(MacOSX)
    case "freebsd" => Some(FreeBSD)
    case "sunos" => Some(SunOS)
    case CygwinPattern(_) => Some(Windows64)
    case LinuxPattern(_) => Some(Linux)
    case _ => None
  }

  private val CygwinPattern = "(cygwin|mingw64|msys).*".r

  private val LinuxPattern = "(linux|linux32|linux64)".r

  val Linux = Platform("LINUX", "Linux")
  val MacOSX = Platform("MAC_OSX", "Mac OSX")
  val Windows64 = Platform("CYGWIN", "Cygwin")
  val FreeBSD = Platform("FREE_BSD", "FreeBSD")
  val SunOS = Platform("SUN_OS", "Solaris")
  val Universal = Platform("UNIVERSAL", "Universal")
}