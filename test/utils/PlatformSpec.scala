package utils

import domain.Platform
import org.scalatest.{Matchers, WordSpec}

class PlatformSpec extends WordSpec with Matchers {
  "platform" should {
    "return some platform when presented with a valid client platform identifier" in {
      Platform("cygwin_nt-6.1") shouldBe Platform.Windows64
      Platform("cygwin_nt-6.1-wow") shouldBe Platform.Windows64
      Platform("cygwin_nt-6.1-wow64") shouldBe Platform.Windows64
      Platform("cygwin_nt-6.3") shouldBe Platform.Windows64
      Platform("cygwin_nt-6.3-wow") shouldBe Platform.Windows64
      Platform("cygwin_nt-10.0") shouldBe Platform.Windows64
      Platform("cygwin_nt-10.0-wow") shouldBe Platform.Windows64
      Platform("darwin") shouldBe Platform.MacX64
      Platform("darwinx64") shouldBe Platform.MacX64
      Platform("darwinarm64") shouldBe Platform.MacARM64
      Platform("freebsd") shouldBe Platform.FreeBSD
      Platform("linux") shouldBe Platform.LinuxX64
      Platform("linux64") shouldBe Platform.LinuxX64
      Platform("linuxx64") shouldBe Platform.LinuxX64
      Platform("linuxarm32sf") shouldBe Platform.LinuxARM32SF
      Platform("linuxarm32hf") shouldBe Platform.LinuxARM32HF
      Platform("linuxarm64") shouldBe Platform.LinuxARM64
      Platform("mingw64_nt-6.1") shouldBe Platform.Windows64
      Platform("mingw64_nt-6.3") shouldBe Platform.Windows64
      Platform("mingw64_nt-10.0") shouldBe Platform.Windows64
      Platform("msys_nt-6.1") shouldBe Platform.Windows64
      Platform("msys_nt-6.3") shouldBe Platform.Windows64
      Platform("msys_nt-10.0") shouldBe Platform.Windows64
      Platform("sunos") shouldBe Platform.SunOS
      Platform("linuxx32") shouldBe Platform.Exotic
      Platform("linux32") shouldBe Platform.Exotic
      Platform("mingw32_nt-6.1") shouldBe Platform.Exotic
      Platform("mingw32_nt-6.1-wow") shouldBe Platform.Exotic
      Platform("mingw32_nt-6.2") shouldBe Platform.Exotic
    }

    "return none when presented with an unknown client platform identifier" in {
      Platform("exotic") shouldBe Platform.Exotic
    }
  }
}
