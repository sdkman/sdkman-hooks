package utils

import domain.Platform
import org.scalatest.{Matchers, WordSpec}

class PlatformSpec extends WordSpec with Matchers {
  "platform" should {
    "return some platform when presented with a valid client platform identifier" in {
      Platform("cygwin_nt-6.1") shouldBe Platform.Windows64Cygwin
      Platform("cygwin_nt-6.1-wow") shouldBe Platform.Windows64Cygwin
      Platform("cygwin_nt-6.1-wow64") shouldBe Platform.Windows64Cygwin
      Platform("cygwin_nt-6.3") shouldBe Platform.Windows64Cygwin
      Platform("cygwin_nt-6.3-wow") shouldBe Platform.Windows64Cygwin
      Platform("cygwin_nt-10.0") shouldBe Platform.Windows64Cygwin
      Platform("cygwin_nt-10.0-wow") shouldBe Platform.Windows64Cygwin
      Platform("darwin") shouldBe Platform.MacOSX
      Platform("darwinx64") shouldBe Platform.MacOSX
      Platform("darwinarm64") shouldBe Platform.MacOSX
      Platform("freebsd") shouldBe Platform.FreeBSD
      Platform("linux") shouldBe Platform.Linux
      Platform("linux64") shouldBe Platform.Linux
      Platform("linuxx64") shouldBe Platform.Linux
      Platform("linuxarm32") shouldBe Platform.Linux
      Platform("linuxarm64") shouldBe Platform.Linux
      Platform("mingw64_nt-6.1") shouldBe Platform.Windows64MinGW
      Platform("mingw64_nt-6.3") shouldBe Platform.Windows64MinGW
      Platform("mingw64_nt-10.0") shouldBe Platform.Windows64MinGW
      Platform("msys_nt-6.1") shouldBe Platform.Windows64MinGW
      Platform("msys_nt-6.3") shouldBe Platform.Windows64MinGW
      Platform("msys_nt-10.0") shouldBe Platform.Windows64MinGW
      Platform("sunos") shouldBe Platform.SunOS
      Platform("linuxx32") shouldBe Platform.Exotic
      Platform("linux32") shouldBe Platform.Exotic
      Platform("mingw32_nt-6.1") shouldBe Platform.Exotic
      Platform("mingw32_nt-6.1-wow") shouldBe Platform.Exotic
      Platform("mingw32_nt-6.2") shouldBe Platform.Exotic
    }

    "return none when presented with an unknown client platform identifier" in {
      Platform("zxspectrum") shouldBe Platform.Exotic
    }
  }
}
