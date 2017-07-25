package utils

import domain.Platform
import org.scalatest.{Matchers, WordSpec}

class PlatformSpec extends WordSpec with Matchers {
  "platform" should {
    "return some platform identifier when presented with a valid unnormalised client identifier" in {
      Platform("CYGWIN_NT-6.1") shouldBe Some(Platform.Windows64Cygwin)
      Platform("CYGWIN_NT-6.1-WOW") shouldBe Some(Platform.Windows64Cygwin)
      Platform("CYGWIN_NT-6.1-WOW64") shouldBe Some(Platform.Windows64Cygwin)
      Platform("CYGWIN_NT-6.3") shouldBe Some(Platform.Windows64Cygwin)
      Platform("CYGWIN_NT-6.3-WOW") shouldBe Some(Platform.Windows64Cygwin)
      Platform("CYGWIN_NT-10.0") shouldBe Some(Platform.Windows64Cygwin)
      Platform("CYGWIN_NT-10.0-WOW") shouldBe Some(Platform.Windows64Cygwin)
      Platform("Darwin") shouldBe Some(Platform.MacOSX)
      Platform("FreeBSD") shouldBe Some(Platform.FreeBSD)
      Platform("Linux") shouldBe Some(Platform.Linux)
      Platform("Linux32") shouldBe Some(Platform.Linux)
      Platform("Linux64") shouldBe Some(Platform.Linux)
      Platform("MINGW32_NT-6.1") shouldBe Some(Platform.Windows64MinGW)
      Platform("MINGW64_NT-6.1") shouldBe Some(Platform.Windows64MinGW)
      Platform("MINGW32_NT-6.1-WOW") shouldBe Some(Platform.Windows64MinGW)
      Platform("MINGW32_NT-6.2") shouldBe Some(Platform.Windows64MinGW)
      Platform("MINGW64_NT-6.3") shouldBe Some(Platform.Windows64MinGW)
      Platform("MINGW64_NT-10.0") shouldBe Some(Platform.Windows64MinGW)
      Platform("MSYS_NT-6.1") shouldBe Some(Platform.Windows64MinGW)
      Platform("MSYS_NT-6.3") shouldBe Some(Platform.Windows64MinGW)
      Platform("MSYS_NT-10.0") shouldBe Some(Platform.Windows64MinGW)
      Platform("SunOS") shouldBe Some(Platform.SunOS)
    }

    "return some platform identifier when presented with a valid normalised uname" in {
      Platform("cygwin_nt-6.1") shouldBe Some(Platform.Windows64Cygwin)
      Platform("cygwin_nt-6.1-wow") shouldBe Some(Platform.Windows64Cygwin)
      Platform("cygwin_nt-6.1-wow64") shouldBe Some(Platform.Windows64Cygwin)
      Platform("cygwin_nt-6.3") shouldBe Some(Platform.Windows64Cygwin)
      Platform("cygwin_nt-6.3-wow") shouldBe Some(Platform.Windows64Cygwin)
      Platform("cygwin_nt-10.0") shouldBe Some(Platform.Windows64Cygwin)
      Platform("cygwin_nt-10.0-wow") shouldBe Some(Platform.Windows64Cygwin)
      Platform("darwin") shouldBe Some(Platform.MacOSX)
      Platform("freebsd") shouldBe Some(Platform.FreeBSD)
      Platform("linux") shouldBe Some(Platform.Linux)
      Platform("linux32") shouldBe Some(Platform.Linux)
      Platform("linux64") shouldBe Some(Platform.Linux)
      Platform("mingw32_nt-6.1") shouldBe Some(Platform.Windows64MinGW)
      Platform("mingw32_nt-6.1-wow") shouldBe Some(Platform.Windows64MinGW)
      Platform("mingw32_nt-6.2") shouldBe Some(Platform.Windows64MinGW)
      Platform("mingw64_nt-6.1") shouldBe Some(Platform.Windows64MinGW)
      Platform("mingw64_nt-6.3") shouldBe Some(Platform.Windows64MinGW)
      Platform("mingw64_nt-10.0") shouldBe Some(Platform.Windows64MinGW)
      Platform("msys_nt-6.1") shouldBe Some(Platform.Windows64MinGW)
      Platform("msys_nt-6.3") shouldBe Some(Platform.Windows64MinGW)
      Platform("msys_nt-10.0") shouldBe Some(Platform.Windows64MinGW)
      Platform("sunos") shouldBe Some(Platform.SunOS)
    }

    "return none when presented with an unknown uname" in {
      Platform("zxspectrum") shouldBe None
      Platform("commodore64") shouldBe None
    }
  }
}
