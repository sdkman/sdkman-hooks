package utils

import domain.Platform
import org.scalatest.{Matchers, WordSpec}

class PlatformSpec extends WordSpec with Matchers {
  "platform" should {
    "return some platform when presented with a valid client platform identifier" in {
      Platform("cygwin_nt-6.1") shouldBe Some(Platform.Windows64Cygwin)
      Platform("cygwin_nt-6.1-wow") shouldBe Some(Platform.Windows64Cygwin)
      Platform("cygwin_nt-6.1-wow64") shouldBe Some(Platform.Windows64Cygwin)
      Platform("cygwin_nt-6.3") shouldBe Some(Platform.Windows64Cygwin)
      Platform("cygwin_nt-6.3-wow") shouldBe Some(Platform.Windows64Cygwin)
      Platform("cygwin_nt-10.0") shouldBe Some(Platform.Windows64Cygwin)
      Platform("cygwin_nt-10.0-wow") shouldBe Some(Platform.Windows64Cygwin)
      Platform("darwin") shouldBe Some(Platform.MacOSX)
      Platform("darwinx64") shouldBe Some(Platform.MacOSX)
      Platform("darwinarm64") shouldBe Some(Platform.MacOSX)
      Platform("freebsd") shouldBe Some(Platform.FreeBSD)
      Platform("linux") shouldBe Some(Platform.Linux)
      Platform("linux32") shouldBe Some(Platform.Linux)
      Platform("linux64") shouldBe Some(Platform.Linux)
      Platform("linuxx32") shouldBe Some(Platform.Linux)
      Platform("linuxx64") shouldBe Some(Platform.Linux)
      Platform("linuxarm32") shouldBe Some(Platform.Linux)
      Platform("linuxarm64") shouldBe Some(Platform.Linux)
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

    "return none when presented with an unknown client platform identifier" in {
      Platform("zxspectrum") shouldBe None
      Platform("commodore64") shouldBe None
    }
  }
}
