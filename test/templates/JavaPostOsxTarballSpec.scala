package templates

import java.nio.file.{Files, Path}

import domain.{Candidate, Platform}
import org.scalatest.{Matchers, WordSpec}

import scala.sys.process._

/**
  * Executes the rendered post-install hook rather than asserting on its text.
  *
  * The hook is sourced into the user's shell, which on macOS is usually zsh. zsh aborts a command
  * whose glob matches nothing (NOMATCH): in a simple command that is recoverable noise, but in a
  * `for` word list it unwinds the caller, bypassing the CLI's
  * `__sdkman_post_installation_hook || return 1` and the hook's own cleanup. Only zsh reproduces
  * the fatal variant — bash aborts the function but carries on — so the suite runs under both
  * shells, and skips zsh where it is not installed.
  */
class JavaPostOsxTarballSpec extends WordSpec with Matchers {

  private val candidate = Candidate("java")
  private val version   = "21.0.3-librca"

  private val installedBinary = s"${candidate.name}-$version/bin/java"

  private val shells = Seq("bash" -> Seq("bash", "-O", "failglob"), "zsh" -> Seq("zsh"))
    .filter { case (shell, _) => Seq("which", shell).!(ProcessLogger(_ => ())) == 0 }

  "the osx java tarball post-install hook" should {

    "repackage a flat tarball layout without tripping over a glob" in {
      eachShell { shell =>
        val result = runHook(shell, Seq("jdk-26.0.2.jdk/bin/java"))
        result.completed shouldBe true
        result.archivedEntries should contain(installedBinary)
        result.globFailures shouldBe empty
      }
    }

    "repackage a bundle tarball layout without tripping over a glob" in {
      eachShell { shell =>
        val result = runHook(shell, Seq("zulu26.jdk/Contents/Home/bin/java"))
        result.completed shouldBe true
        result.archivedEntries should contain(installedBinary)
        result.globFailures shouldBe empty
      }
    }

    "return to its caller when the extracted archive is empty" in {
      eachShell { shell =>
        runHook(shell, Seq.empty).completed shouldBe true
      }
    }
  }

  private def eachShell(assertion: Seq[String] => Unit): Unit =
    shells.foreach { case (shell, command) => withClue(s"under $shell: ")(assertion(command)) }

  private case class Result(completed: Boolean, archivedEntries: Seq[String], output: String) {
    val globFailures: Seq[String] = output.split('\n').filter(_.contains("no match")).toSeq
  }

  private def runHook(shell: Seq[String], files: Seq[String]): Result = {
    val root = Files.createTempDirectory("osx-tarball-hook")

    val source = Files.createDirectories(root.resolve("src"))
    files.foreach { file =>
      val target = source.resolve(file)
      Files.createDirectories(target.getParent)
      Files.write(target, "binary".getBytes)
    }

    val sdkmanDir = root.resolve("sdkman")
    Files.createDirectories(sdkmanDir.resolve("tmp"))

    val binaryInput = sdkmanDir.resolve("tmp/in.tar.gz")
    Seq("tar", "czf", binaryInput.toString, "-C", source.toString, ".").!!

    val zipOutput = sdkmanDir.resolve(s"tmp/${candidate.name}-$version.zip")
    val hook      = write(root.resolve("hook.sh"), rendered)

    // mirrors the CLI's call site: `__sdkman_post_installation_hook || return 1` inside a function
    val driver = write(
      root.resolve("driver.sh"),
      s"""__sdkman_echo_debug() { :; }
         |__sdkman_echo_green() { :; }
         |export SDKMAN_DIR="$sdkmanDir"
         |binary_input="$binaryInput"
         |zip_output="$zipOutput"
         |source "$hook"
         |__sdkman_install() { __sdkman_post_installation_hook || return 1; }
         |__sdkman_install
         |echo "$CompletedMarker"
         |""".stripMargin
    )

    val output = new StringBuilder
    (shell :+ driver.toString) ! ProcessLogger(line => output.append(line).append('\n'))

    val entries =
      if (Files.exists(zipOutput)) Seq("unzip", "-Z1", zipOutput.toString).!!.split('\n').toSeq
      else Seq.empty

    Result(output.toString.contains(CompletedMarker), entries, output.toString)
  }

  private val CompletedMarker = "HOOK_RETURNED_TO_CALLER"

  private def rendered =
    views.txt.java_post_osx_tarball(candidate, version, Platform.MacARM64).body

  private def write(path: Path, content: String): Path =
    Files.write(path, content.getBytes)
}
